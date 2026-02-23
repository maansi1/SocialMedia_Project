package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.PostRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public Post createPost(Post post) {
        // Ensure user exists (important when client sends user id)
        Long userId = (post.getUser() != null) ? post.getUser().getId() : null;
        if (userId == null) throw new NotFoundException("User id is required for post");

        User u = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        post.setUser(u);
        return postRepo.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found with id: " + id));
    }

    public List<Post> getPostsByUserId(Long userId) {
        // optional: validate user exists
        userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        return postRepo.findByUserId(userId);
    }

    public void deletePost(Long id) {
        Post p = getPostById(id);
        postRepo.delete(p);
    }
}



