package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title must be 2-100 chars")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Caption is required")
    @Size(min = 2, max = 1000, message = "Caption must be 2-1000 chars")
    @Column(nullable = false, length = 1000)
    private String caption;

    // optional
    @Size(max = 500, message = "Image URL too long")
    private String imageUrl;

    @NotNull(message = "User is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();
}
