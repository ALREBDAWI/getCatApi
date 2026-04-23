package com.getcat.api.controller;


import com.getcat.api.model.Post;
import com.getcat.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@RequestBody Integer id, Post post) {
        return postService.updatePost(id,post);
    }

    @DeleteMapping("/{id}")
    public void softDeletePost(@PathVariable Integer id) {
        postService.hardDeletePost(id);
    }

    @DeleteMapping
    public void HardDeletePost(@RequestBody Integer id) {
        postService.hardDeletePost(id);
    }
}
