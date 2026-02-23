package com.example.demo.Service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.Model.Like;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.LikeRepo;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepo likeRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;

    public Like likePost(Like like) {
        Long userId = (like.getUser() != null) ? like.getUser().getId() : null;
        Long postId = (like.getPost() != null) ? like.getPost().getId() : null;

        if (userId == null) throw new NotFoundException("User id is required for like");
        if (postId == null) throw new NotFoundException("Post id is required for like");

        User u = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        Post p = postRepo.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + postId));

        like.setUser(u);
        like.setPost(p);

        return likeRepo.save(like);
    }

    public void dislikePost(Long id) {
        Like l = likeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Like not found with id: " + id));
        likeRepo.delete(l);
    }
}

