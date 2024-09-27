package com.codewithbappi.blog.controller;

import com.codewithbappi.blog.payloads.ApiResponse;
import com.codewithbappi.blog.payloads.CommentDto;
import com.codewithbappi.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController
{

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable("postId") Integer postId)
    {
        CommentDto commentDto = this.commentService.createComment(comment,postId);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> createComment(@PathVariable("postId") Integer commentId)
    {
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment is successfully deleted",true),HttpStatus.OK);
    }
}
