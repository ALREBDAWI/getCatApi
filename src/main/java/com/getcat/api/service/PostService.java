package com.getcat.api.service;

import com.getcat.api.model.Post;
import com.getcat.api.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepo;

    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }

    public Post getPostById(Integer id){
        return postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public Post createPost(Post post){
        return postRepo.save(post);
    }

    public Post updatePost(Integer id, Post post){
        Post existing = getPostById(id);

        if (post.getDescription() != null)
            existing.setDescription(post.getDescription());

        if (post.getAnimalPhoto() != null)
            existing.setAnimalPhoto(post.getAnimalPhoto());

        if (post.getStartDate() != null)
            existing.setStartDate(post.getStartDate());

        if (post.getEndDate() != null)
            existing.setEndDate(post.getEndDate());

        return postRepo.save(existing);
    }

    public void softDeletePost(Integer id){
        Post post = getPostById(id);
        post.setIsDeleted(true);
        postRepo.save(post);
    }

    public void hardDeletePost(Integer id){
        Post post = getPostById(id);
        postRepo.delete(post);
    }
}
