package com.codewithbappi.blog.impl;

import com.codewithbappi.blog.Exceptions.ResourceNotFoundException;
import com.codewithbappi.blog.entities.Posts;
import com.codewithbappi.blog.payloads.CommentDto;
import com.codewithbappi.blog.repositories.CommentRepo;
import com.codewithbappi.blog.repositories.PostRepo;
import com.codewithbappi.blog.services.CommentService;
import com.codewithbappi.blog.entities.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService
{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId)
    {
        Posts posts = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPosts(posts);

        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId)
    {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        this.commentRepo.delete(com);
    }
}
