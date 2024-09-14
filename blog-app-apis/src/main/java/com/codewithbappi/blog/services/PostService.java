package com.codewithbappi.blog.services;

import com.codewithbappi.blog.entities.Post;
import com.codewithbappi.blog.payloads.PostDto;

import java.util.List;

public interface PostService
{
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    Post updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<Post> searchPosts(String keyword);
}
