package com.project.questapp.service;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.requests.PostSaveRequest;
import com.project.questapp.requests.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public List<Post> getAll(Optional<Long> userId) {
        if (userId.isPresent()){
            return postRepository.findByUserId(userId);
        }
        return postRepository.findAll();
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
