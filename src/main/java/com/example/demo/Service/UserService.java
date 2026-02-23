package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists: " + user.getEmail());
        }
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new BadRequestException("Username already exists: " + user.getUsername());
        }
        return userRepo.save(user);
    }
}
