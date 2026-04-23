package com.getcat.api.service;

import com.getcat.api.model.Comment;
import com.getcat.api.model.Post;
import com.getcat.api.model.User;
import com.getcat.api.repo.CommentRepo;
import com.getcat.api.repo.PostRepo;
import com.getcat.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;


    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }

    public Comment getCommentById(Integer id){
        return commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    public  Comment createComment(String content, Integer user_id, Integer post_id) {
        User user = userRepo.findById(user_id).orElseThrow(() -> new RuntimeException("Comment not found with id: " + user_id));
        Post post = postRepo.findById(post_id).orElseThrow(() -> new RuntimeException("Post not found with id: " + post_id));

        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .post(post)
                .build();

        return commentRepo.save(comment);
    }

    public Comment updateComment(Integer id, String content){
        Comment comment = getCommentById(id);

        if (content != null) {
            comment.setContent(content);
        }

        return commentRepo.save(comment);
    }

    public void softDeleteComment(Integer id){
        Comment comment = getCommentById(id);

        if (comment.getDeletedAt() != null) {
            throw new RuntimeException("Comment already deleted");
        }

        comment.setDeletedAt(LocalDateTime.now());
        commentRepo.save(comment);
    }

    public void hardDeleteComment(Integer id){
        Comment comment = getCommentById(id);
        commentRepo.delete(comment);
    }


}
