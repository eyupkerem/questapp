package com.project.questapp.requests;

import lombok.Data;

@Data
public class PostSaveRequest {
    private Long id;
    private String text;
    private String title;
    private Long userId;
}
