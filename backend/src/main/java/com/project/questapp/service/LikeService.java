package com.project.questapp.service;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.requests.LikeSaveRequest;
import com.project.questapp.response.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {


    @Autowired
    private  LikeRepository likeRepository;
    @Autowired
    private  UserService userService;
    @Lazy
    @Autowired
    private  PostService postService;

    public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list=null;
        if (userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            list =  likeRepository.findByUserId(userId);
        }
        else if (postId.isPresent()){
            list =  likeRepository.findByPostId(postId);
        }
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like save(LikeSaveRequest request) {
        User user = userService.findById(request.getUserId());
        Post post = postService.findById(request.getPostId());

        if (user != null && post != null){

            Like toSave = new Like();

            toSave.setId(request.getId());
            toSave.setPost(post);
            toSave.setUser(user);

            return likeRepository.save(toSave);
        }
        return null;
    }

    public void deleteById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
