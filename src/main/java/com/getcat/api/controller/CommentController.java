package com.getcat.api.controller;

import com.getcat.api.model.Comment;
import com.getcat.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Integer id){
        return commentService.getCommentById(id);
    }

    @DeleteMapping("/{id}")
    public void softDeleteComment(@PathVariable Integer id){
        commentService.softDeleteComment(id);
    }

    @DeleteMapping("/{id}/hard")
    public void hardDeleteComment(@PathVariable Integer id){
        commentService.hardDeleteComment(id);
    }
}
