package com.project.questapp.service;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.requests.LikeSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            return likeRepository.findByUserId(userId);
        }
        else if (postId.isPresent()){
            return likeRepository.findByPostId(postId);
        }
        return likeRepository.findAll();
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
