package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Like;
import com.example.demo.Service.LikeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<String> likePost(@Valid @RequestBody Like like) {
        Like saved = likeService.likePost(like);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Liked successfully. likeId=" + saved.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> dislike(@PathVariable Long id) {
        likeService.dislikePost(id);
        return ResponseEntity.ok("Like removed successfully");
    }
}