package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Comment;
import com.example.demo.Service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // ✅ POST /comments (Return String to avoid infinite JSON nesting)
    @PostMapping
    public ResponseEntity<String> addComment(@Valid @RequestBody Comment comment) {
        Comment saved = commentService.addComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Comment added successfully. commentId=" + saved.getId());
    }

    // ✅ GET /comments/post/{postId}
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    // ✅ GET /comments/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }

    // ✅ DELETE /comments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}