package com.project.questapp.service;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.requests.PostSaveRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.response.LikeResponse;
import com.project.questapp.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    private LikeService likeService;

    public List<PostResponse> getAll(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()) {
            list = postRepository.findByUserId(userId);
        }else
            list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikes(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes);}).collect(Collectors.toList());
    }
    public Post findById(Long postId) {
       return postRepository.findById(postId).orElse(null);
    }

    public Post save(PostSaveRequest request) {

        User foundUser = userService.findById(request.getUserId());

        if (foundUser == null){
            return null;
        }
        Post toSave = new Post();
        toSave.setId(request.getId());
        toSave.setText(request.getText());
        toSave.setTitle(request.getTitle());
        toSave.setUser(foundUser);


        return postRepository.save(toSave);

    }

    public Post updatePost(Long postId, PostUpdateRequest updateRequest) {

        Optional<Post> tempPost = postRepository.findById(postId);

        if(tempPost.isPresent()){
            Post toUpdate=tempPost.get();
            toUpdate.setText(updateRequest.getText());
            toUpdate.setTitle(updateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
