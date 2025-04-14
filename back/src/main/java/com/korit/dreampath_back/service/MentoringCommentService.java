package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentPageDto;
import com.korit.dreampath_back.dto.request.comment.ReqMentoringCommentUpdateDto;
import com.korit.dreampath_back.dto.response.comment.RespMentoringCommentPageDto;
import com.korit.dreampath_back.entity.CommentSearch;
import com.korit.dreampath_back.entity.MentoringComment;
import com.korit.dreampath_back.entity.User;
import com.korit.dreampath_back.entity.UserRole;
import com.korit.dreampath_back.mapper.PostMapper;
import com.korit.dreampath_back.repository.MentoringCommentRepository;
import com.korit.dreampath_back.repository.PostRepository;
import com.korit.dreampath_back.repository.UserRepository;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MentoringCommentService {

    @Autowired
    private MentoringCommentRepository mentoringCommentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostMapper postMapper;

    public RespMentoringCommentPageDto getCommentWithPage(ReqMentoringCommentPageDto dto, int postId) {


        int startIndex = (dto.getPage() - 1) * dto.getLimitCount();
        List<CommentSearch> commentSearchLists = mentoringCommentRepository.findCommentByPostId(startIndex, dto.getLimitCount(), postId);
        int totalComments = mentoringCommentRepository.getCountsByPostId(postId);
        int totalPages = totalComments % dto.getLimitCount() == 0
                ? totalComments / dto.getLimitCount()
                : totalComments / dto.getLimitCount() + 1;

        RespMentoringCommentPageDto respMentoringCommentPageDto =RespMentoringCommentPageDto.builder()
                .page(dto.getPage())
                .limitCount(dto.getLimitCount())
                .isFirstPage(dto.getPage() == 1)
                .isLastPage(dto.getPage() == totalPages)
                .totalPages(totalPages)
                .totalElements(totalComments)
                .nextPage(dto.getPage() == totalPages ? totalPages : dto.getPage() + 1)
                .commentSearchList(commentSearchLists)
                .build();
        return respMentoringCommentPageDto;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean addComment(User user, ReqMentoringCommentDto commentDto){

        int boardId = postMapper.getBoardIdByPostId(commentDto.getPostId());

        if ( boardId == 1 && (commentDto.getStarPoint() < 1 || commentDto.getStarPoint() > 5)) {

            return false;
        }


        MentoringComment newComment = MentoringComment.builder()
                .postId(commentDto.getPostId())
                .userId(user.getUserId())
                .starPoint(commentDto.getStarPoint())
                .content(commentDto.getContent())
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        boolean isAdded = mentoringCommentRepository.addComment(newComment) > 0;


        if(boardId == 1){
            double postStarPoint = postRepository.getPostStarPointAvgByPostId(commentDto.getPostId());
            postRepository.updatePostStarPointAvgByPostId(commentDto.getPostId(), postStarPoint);

            int userId = userRepository.getUserIdByPostId(commentDto.getPostId());
            double userStarPoint = userRepository.getUserStarPointAvg(userId);
            userRepository.updateStarPoint(userId, userStarPoint);
        }


        return isAdded;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateComment(PrincipalUser principalUser, ReqMentoringCommentUpdateDto updateDto) {

        MentoringComment updateMentoringComment = MentoringComment.builder()
                .commentId(updateDto.getCommentId())
                .userId(principalUser.getUser().getUserId())
                .content(updateDto.getContent())
                .starPoint(updateDto.getStarPoint())
                .build();

        boolean isUpdated = mentoringCommentRepository.updateComment(updateMentoringComment) > 0;

        int postId = postMapper.selectPostIdByCommentId(updateDto.getCommentId());
        int boardId = postMapper.getBoardIdByPostId(postId);

        if(boardId == 1) {

            double postStarPoint = postRepository.getPostStarPointAvgByPostId(postId);
            postRepository.updatePostStarPointAvgByPostId(postId, postStarPoint);

            int userId = userRepository.getUserIdByPostId(postId);
            double userStarPoint = userRepository.getUserStarPointAvg(userId);
            userRepository.updateStarPoint(userId, userStarPoint);
        }


        return isUpdated;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean deleteComment(PrincipalUser principalUser, int commentId, int userId) {

        boolean isAdmin = false;
        for(UserRole role : principalUser.getUser().getUserRoles()) {
            if( role.getRoleId() == 3) {
                isAdmin = true;
            }
        }
        if(userId != principalUser.getUser().getUserId() && !isAdmin) {
            return false;
        }

        int postId = postMapper.selectPostIdByCommentId(commentId);
        int boardId = postMapper.getBoardIdByPostId(postId);

        if(boardId == 1) {
            double postStarPoint = postRepository.getPostStarPointAvgByPostId(postId);
            postRepository.updatePostStarPointAvgByPostId(postId, postStarPoint);

            int mentoId = userRepository.getUserIdByPostId(postId);
            double userStarPoint = userRepository.getUserStarPointAvg(mentoId);
            userRepository.updateStarPoint(mentoId, userStarPoint);
        }

        boolean isDeleted = mentoringCommentRepository.deleteComment(commentId) > 0;

        if(mentoringCommentRepository.getCountsByPostId(postId) == 0) {
            postRepository.updatePostStarPointAvgByPostId(postId, 0);
        }


        return isDeleted;
    }


}
