package com.project.questapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "postId")
    private Long postId;
    @Column(name = "userI")
    private Long userId;
    @Lob
    @Column(name = "text" , columnDefinition = "text")
    private String text;

}
