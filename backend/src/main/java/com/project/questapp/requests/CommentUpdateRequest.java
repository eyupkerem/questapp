package com.project.questapp.requests;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CommentUpdateRequest {
    private String text;
}
