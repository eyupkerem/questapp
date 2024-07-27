package com.project.questapp.service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.requests.CommentSaveRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private  UserService userService;
    @Autowired
    @Lazy
    private   PostService postService;


    public List<Comment> getAllComments(Optional<Long> userId,Optional<Long> postId) {

        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if (userId.isPresent()) {
        return commentRepository.findByUserId(userId.get());
        }
        else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }
        return commentRepository.findAll();
    }

    public Comment getById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment save(CommentSaveRequest request) {

        User user=userService.findById(request.getUserId());
        Post post=postService.findById(request.getPostId());

        if (user != null && post != null){

            Comment toSave = new Comment();

            toSave.setId(request.getId());
            toSave.setPost(post);
            toSave.setUser(user);
            toSave.setText(request.getText());
            return commentRepository.save(toSave);

        }
        return null;
    }

    public Comment updateComment(Long commentId, CommentUpdateRequest request) {

        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment toUpdateComment = comment.get();
            toUpdateComment.setText(request.getText());
            return commentRepository.save(toUpdateComment);
        }
        return null;
    }

    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
