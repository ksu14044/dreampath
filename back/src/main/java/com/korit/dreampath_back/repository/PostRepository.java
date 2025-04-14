package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.entity.PostDetail;
import com.korit.dreampath_back.entity.PostListPage;
import com.korit.dreampath_back.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @Autowired
    private PostMapper postMapper;

//    searchTxt로 찾은 postList count
    public int findPostListCountAllBySearchTxt(int boardId, String searchTxt) {
        return postMapper.selectPostListCountAllBySearchTxt(boardId, searchTxt);
    }

//    게시글 등록
    public int addPost(Post post) {
        return postMapper.createPost(post);
    }


//    전체 조회 (검색 조회 포함)
    public Optional<List<PostListPage>> findPostList(int boardId, int startIdx, int limitCount, String order, String status, String category, String searchTxt) {
        return Optional.ofNullable(postMapper.selectPostList(boardId, startIdx, limitCount, order,status, category, searchTxt));
    }
    public PostListPage getPostByPostId(int postId) {
        return postMapper.getPostByPostId(postId);
    }

//    게시글 상세 조회
    public Optional<PostDetail> findPostDetail(int postId) {
        return Optional.ofNullable(postMapper.selectPostDetail(postId));
    }

//    게시글 수정
    public int updatedPost(Post post) {
        return postMapper.updatedPost(post);
    }

//    게시글 삭제
    public int deletePost(int postId, int userId) {
        return postMapper.deletePost(postId, userId);
    }

//    게시글 조회수

    @Async
    public void updatePostViewCount(int postId) {

        postMapper.updatePostViewCount(postId);
    }

//    게시글 상태 수정
    public int updatePostStatusClosedRecruiting(int postId,  int userId) {
        return postMapper.updatePostStatusClosedRecruiting(postId, userId);
    }

    public int updatePostStatusRecruiting(int postId,  int userId) {
        return postMapper.updatePostStatusRecruiting(postId, userId);
    }

    public boolean isRecruited(int postId) {
        return postMapper.getPostStatusByPostId(postId).equals("closedRecruitment");
    }

    public double getPostStarPointAvgByPostId(int postId) {
        return postMapper.getPostStarPointAvgByPostId(postId);
    }
    public int updatePostStarPointAvgByPostId(int postId, double postStarPoint) {
        return postMapper.updatePostStarPointAvgByPostId(postId, postStarPoint);
    }

    public int selectPostIdByCommentId(int commentId) {
        return postMapper.selectPostIdByCommentId(commentId);
    }

    public List<PostDetail> getBeforeTodayPosts() {
        return postMapper.getBeforeTodayPosts();
    }

    public int getBoardIdByPostId(int postId) {
        return postMapper.getBoardIdByPostId(postId);
    }
}



