package com.project.questapp.controller;

import com.project.questapp.entities.Post;
import com.project.questapp.requests.PostSaveRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getAll(@RequestParam Optional<Long> userId){
        return postService.getAll(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId){
        return postService.findById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostSaveRequest postSaveRequest){
        return postService.save(postSaveRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId , @RequestBody PostUpdateRequest updateRequest){
        return postService.updatePost(postId , updateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }



}
