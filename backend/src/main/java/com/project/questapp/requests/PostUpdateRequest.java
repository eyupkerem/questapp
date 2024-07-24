package com.project.questapp.requests;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostUpdateRequest {
    private String text;
    private String title;
}
