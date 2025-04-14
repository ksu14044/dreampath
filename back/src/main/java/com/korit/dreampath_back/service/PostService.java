package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.ReqApplyEmailDto;
import com.korit.dreampath_back.dto.request.post.ReqPostCreateDto;
import com.korit.dreampath_back.dto.request.post.ReqPostLikeDto;
import com.korit.dreampath_back.dto.request.post.ReqPostSearchDto;
import com.korit.dreampath_back.dto.request.post.ReqPostUpdateDto;
import com.korit.dreampath_back.entity.*;
import com.korit.dreampath_back.repository.*;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private FileService fileService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplyService applyService;

    public int getPostListCountAllBySearchTxt(int boardId, String searchTxt) {
        return postRepository.findPostListCountAllBySearchTxt(boardId, searchTxt);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addPost(User user, ReqPostCreateDto createDto) {

        if (createDto.getBoardId() == 1) {
            //        remaining이 1보다 작으면 등록 안됨
            if (user.getRemaining() < 1) {
                return false;
            }
            if (!user.getRoleName().equals("ROLE_MENTO")) {
                return false;
            }

            userRepository.updateRemainingCount(user.getUserId());
        }

        final String PROFILE_IMG_FILE_PATH = "/upload/user/post";
        String saveFilename = fileService.saveFile(PROFILE_IMG_FILE_PATH, createDto.getFile()); // 폴더에 저정

        LocalDate today = LocalDate.now();

        Post newPost = Post.builder()
                .boardId(createDto.getBoardId())
                .userId(user.getUserId())
                .categoryId(createDto.getCategoryId())
                .title(createDto.getTitle())
                .content(createDto.getContent())
                .mentoringAddress(createDto.getMentoringAddress())
                .startDate(createDto.getStartDate())
                .endDate(createDto.getEndDate())
                .status((createDto.getStartDate().isBefore(today) || createDto.getStartDate().isEqual(today)) && (createDto.getEndDate().isAfter(today) || createDto.getEndDate().isEqual(today)) ? "recruiting" : "closedRecruitment")
                .attachedFiles(saveFilename)
                .build();

        return postRepository.addPost(newPost) > 0 ? true : false;
    }

    public List<PostListPage> getPostList(int boardId, ReqPostSearchDto searchDto) throws NotFoundException {

//        int boardId = boardRepository.findBoardIdByBoardName(boardName).getBoardId();

        int startIdx = (searchDto.getPage() - 1) * searchDto.getLimitCount();

        return postRepository.findPostList(boardId, startIdx, searchDto.getLimitCount(), searchDto.getOrder(), searchDto.getStatus(), searchDto.getCategory(), searchDto.getSearchTxt())
                .orElseThrow(() -> new NotFoundException("검색된 게시글이 없습니다."));
    }

    @Transactional(rollbackFor = Exception.class)
    public PostDetail getPostDetail(PrincipalUser principalUser, int postId) throws NotFoundException {
//        조회수 올리기
//        postRepository.updatePostViewCount(postId);

        boolean isApplied = applyService.isApplied(principalUser, postId);
        PostDetail postDetail = postRepository.findPostDetail(postId).orElseThrow(() -> new NotFoundException("잘못된 postId 입니다."));
        postDetail.setApply(isApplied);
        return postDetail;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updatedPost(User user, ReqPostUpdateDto updateDto) {
        if (updateDto.getFile() != null) {
        }

        PostDetail postdetail = postRepository.findPostDetail(updateDto.getPostId()).get();

        final String PROFILE_IMG_FILE_PATH = "/upload/user/post";
        String saveFilename = fileService.saveFile(PROFILE_IMG_FILE_PATH, updateDto.getFile()); // 폴더에 저정

        LocalDate today = LocalDate.now();
        Post newPost = Post.builder()
                .postId(updateDto.getPostId())
                .userId(user.getUserId())
                .categoryId(updateDto.getCategoryId())
                .title(updateDto.getTitle())
                .content(updateDto.getContent())
                .mentoringAddress(updateDto.getMentoringAddress())
                .startDate(updateDto.getStartDate())
                .endDate(updateDto.getEndDate())
                .status((updateDto.getStartDate().isBefore(today) || updateDto.getStartDate().isEqual(today)) && (updateDto.getEndDate().isAfter(today) || updateDto.getEndDate().isEqual(today)) ? "recruiting" : "closedRecruitment")
                .attachedFiles(saveFilename)
                .build();


        if (postdetail.getAttachedFiles() != null) {
            fileService.deleteFile(PROFILE_IMG_FILE_PATH + " / " + postdetail.getAttachedFiles());
        }


        return postRepository.updatedPost(newPost) > 0 ? true : false;
    }

    public boolean deletePost(int postId, User user) {
        return postRepository.deletePost(postId, user.getUserId()) > 0 ? true : false;
    }


//    public void updatePostViewCount(int postId) {
//        postRepository.updatePostViewCount(postId);
//    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addPostLike(User user, int postId) throws Exception {
        int postUserId = postRepository.getPostByPostId(postId).getUserId();

        if (postUserId == user.getUserId()) {
            throw new Exception("본인이 작성한 게시글은 좋아요할 수 없습니다.");
        }

        return postLikeRepository.addPostLike(user.getUserId(), postId) > 0 ? true : false;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deletePostLike(User user, int postId) {
        return postLikeRepository.deletePostLike(user.getUserId(), postId) > 0 ? true : false;
    }

    public PostLike findPostMyLike(User user, int postId) {
        return postLikeRepository.findPostLikeUserByUserId(user.getUserId(), postId);
    }

    public List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public String updatePostStatus(int postId, User user) {
        String status = "";
        if (postRepository.isRecruited(postId)) {
            postRepository.updatePostStatusRecruiting(postId, user.getUserId());
            status = "recruiting";
        } else {
            postRepository.updatePostStatusClosedRecruiting(postId, user.getUserId());
            status = "closedRecruitment";
        }
        return status;
    }


}
