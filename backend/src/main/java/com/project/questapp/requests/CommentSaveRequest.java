package com.project.questapp.requests;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CommentSaveRequest {

    private Long id;
    private Long userId;
    private Long postId;
    private String text;


}
