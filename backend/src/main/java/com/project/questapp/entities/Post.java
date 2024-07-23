package com.project.questapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "title")
    private String title;
    @Lob
    @Column(columnDefinition = "text" , name = "text")
    String text;


}
