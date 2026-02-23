package com.example.demo.Service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.Model.Follow;
import com.example.demo.Model.User;
import com.example.demo.Repository.FollowRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepo followRepo;
    private final UserRepo userRepo;

    public String follow(Long followerId, Long followingId) {

        if (followerId == null || followingId == null) {
            throw new BadRequestException("followerId and followingId are required");
        }

        if (followerId.equals(followingId)) {
            throw new BadRequestException("You cannot follow yourself");
        }

        boolean already = followRepo.findByFollowerIdAndFollowingId(followerId, followingId).isPresent();
        if (already) return "Already following";

        User follower = userRepo.findById(followerId)
                .orElseThrow(() -> new NotFoundException("Follower user not found: " + followerId));
        User following = userRepo.findById(followingId)
                .orElseThrow(() -> new NotFoundException("Following user not found: " + followingId));

        Follow f = new Follow();
        f.setFollower(follower);
        f.setFollowing(following);

        followRepo.save(f);
        return "Followed successfully";
    }

    public String unfollow(Long followerId, Long followingId) {
        if (followerId == null || followingId == null) {
            throw new BadRequestException("followerId and followingId are required");
        }

        return followRepo.findByFollowerIdAndFollowingId(followerId, followingId)
                .map(existing -> {
                    followRepo.delete(existing);
                    return "Unfollowed successfully";
                })
                .orElse("You are not following this user");
    }

    public long followersCount(Long userId) {
        userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));
        return followRepo.countByFollowingId(userId);
    }

    public long followingCount(Long userId) {
        userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));
        return followRepo.countByFollowerId(userId);
    }
}
