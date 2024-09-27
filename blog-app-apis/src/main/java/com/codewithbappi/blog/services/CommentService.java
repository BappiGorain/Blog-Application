package com.codewithbappi.blog.services;
import com.codewithbappi.blog.payloads.CommentDto;


public interface CommentService
{
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
