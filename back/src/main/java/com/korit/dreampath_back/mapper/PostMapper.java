package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.entity.PostDetail;
import com.korit.dreampath_back.entity.PostListPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    int selectPostListCountAllBySearchTxt(
            @Param("boardId") int boardId,
            @Param("searchTxt") String searchTxt
    );
//    게시글 등록
    int createPost(Post post);

//    전체 리스트 조회 (검색 조회 포함)
    List<PostListPage> selectPostList(
            @Param("boardId") int boardId,
            @Param("startIdx") int startIdx,
            @Param("limitCount") int limitCount,
            @Param("order") String order,
            @Param("status") String status,
            @Param("category") String category,
            @Param("searchTxt")String searchTxt
    );


    PostListPage getPostByPostId(
            @Param("postId") int postId
    );

    //    게시글 상세 조회
    PostDetail selectPostDetail(
            @Param("postId") int postId
    );

//    게시글 수정
    int updatedPost(Post post);

//    게시글 삭제
    int deletePost(int postId, int userId);

//    게시글 조회수
    int updatePostViewCount(int postId);

    int updatePostStatusClosedRecruiting(int postId, int userId);

    int updatePostStatusRecruiting(int postId, int userId);

    String getPostStatusByPostId(int postId);

//    별점 가져오기
    double getPostStarPointAvgByPostId(int postId);

    int updatePostStarPointAvgByPostId(int postId, double postStarPoint);

    int selectPostIdByCommentId(int commentId);

    List<PostDetail> getBeforeTodayPosts();

    int getBoardIdByPostId(int postId);
}
