package com.korit.dreampath_back.service;

import com.korit.dreampath_back.entity.Post;
import com.korit.dreampath_back.entity.PostDetail;
import com.korit.dreampath_back.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    private PostRepository postRepository;

    @Scheduled(cron = "${schedule.cron}" )
@Transactional(rollbackFor = Exception.class)
    public void updateRecruiting() {
//        boardId 가 1번인 post 가지고 와서 지금 날짜보다 이전인거 전부다 업데이트
        List<PostDetail> before = postRepository.getBeforeTodayPosts();

        for(PostDetail detail : before) {
            postRepository.updatePostStatusClosedRecruiting(detail.getPostId(), 0);
        }


    }
}
