package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.Model.Comment;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.CommentRepo;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public Comment addComment(Comment comment) {
        Long postId = (comment.getPost() != null) ? comment.getPost().getId() : null;
        Long userId = (comment.getUser() != null) ? comment.getUser().getId() : null;

        if (postId == null) throw new NotFoundException("Post id is required for comment");
        if (userId == null) throw new NotFoundException("User id is required for comment");

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + postId));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        comment.setPost(post);
        comment.setUser(user);

        return commentRepo.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        // optional: check post exists
        postRepo.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + postId));
        return commentRepo.findByPostId(postId);
    }

    public List<Comment> getCommentsByUserId(Long userId) {
        userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        return commentRepo.findByUserId(userId);
    }

    public void deleteComment(Long id) {
        Comment c = commentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id: " + id));
        commentRepo.delete(c);
    }
}
