package com.project.questapp.controller;

import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentSaveRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    @GetMapping
    public List<Comment> getAll(@RequestParam Optional<Long> userId , @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId, postId);
    }
    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId){
        return commentService.getById(commentId);
    }

    @PostMapping
    public Comment saveComment(@RequestBody CommentSaveRequest request){
        return commentService.save(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request){
        return commentService.updateComment(commentId,request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteById(commentId);
    }
}
