package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.Like;

public interface LikeRepo extends JpaRepository<Like, Long> {}