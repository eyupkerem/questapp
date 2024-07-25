package com.project.questapp.controller;

import com.project.questapp.entities.Like;
import com.project.questapp.requests.LikeSaveRequest;
import com.project.questapp.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public List<Like> getAll(@RequestParam Optional<Long> userId ,
                             @RequestParam Optional<Long> postId){
        return likeService.getAllLikes(userId,postId);
    }

    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId){
        return likeService.getById(likeId);
    }

    @PostMapping
    public Like saveLike(@RequestBody LikeSaveRequest request){
        return likeService.save(request);
    }

    @DeleteMapping("/{likeId}")
        public void deleteLike(@PathVariable Long likeId){
        likeService.deleteById(likeId);
        }






}
