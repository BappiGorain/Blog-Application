package com.codewithbappi.blog.controller;

import com.codewithbappi.blog.config.AppConstants;
import com.codewithbappi.blog.payloads.ApiResponse;
import com.codewithbappi.blog.payloads.PostDto;
import com.codewithbappi.blog.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                    @RequestParam (value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection)
    {

        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDirection);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }


    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id)
    {
        PostDto post = this.postService.getPostById(id);
        return new ResponseEntity<PostDto>(post,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable("postId") Integer postId)
    {
        this.postService.deletePost(postId);
        return new ApiResponse("Post deleted successfully",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post, @PathVariable Integer postId)
    {
        PostDto postDto = this.postService.updatePost(post,postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable("keywords") String keywords)
    {
        List<PostDto> dto = this.postService.searchPosts(keywords);
        return  new ResponseEntity<List<PostDto>>(dto,HttpStatus.OK);
    }


}
