package com.example.demo.Controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<String> follow(
            @RequestParam("followerId") Long followerId,
            @RequestParam("followingId") Long followingId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(followService.follow(followerId, followingId));
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollow(
            @RequestParam("followerId") Long followerId,
            @RequestParam("followingId") Long followingId) {

        return ResponseEntity.ok(
                followService.unfollow(followerId, followingId));
    }

    @GetMapping("/{userId}/followers-count")
    public ResponseEntity<Long> followersCount(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(followService.followersCount(userId));
    }

    @GetMapping("/{userId}/following-count")
    public ResponseEntity<Long> followingCount(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(followService.followingCount(userId));
    }
}