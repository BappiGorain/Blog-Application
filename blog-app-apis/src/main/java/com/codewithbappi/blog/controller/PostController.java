package com.codewithbappi.blog.controller;

import com.codewithbappi.blog.payloads.PostDto;
import com.codewithbappi.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController
{
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId)
    {
        PostDto createdPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto> (createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId)
    {
        List<PostDto> returnedPosts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(returnedPosts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId)
    {
        List<PostDto> returnedPosts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(returnedPosts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts()
    {
        List<PostDto> allPosts= this.postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(allPosts,HttpStatus.OK);
    }


    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id)
    {
        PostDto post = this.postService.getPostById(id);
        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }



}
