package com.codewithbappi.blog.impl;

import com.codewithbappi.blog.Exceptions.ResourceNotFoundException;
import com.codewithbappi.blog.entities.Category;
import com.codewithbappi.blog.entities.Post;
import com.codewithbappi.blog.entities.User;
import com.codewithbappi.blog.payloads.PostDto;
import com.codewithbappi.blog.repositories.CategoryRepo;
import com.codewithbappi.blog.repositories.PostRepo;
import com.codewithbappi.blog.repositories.UserRepo;
import com.codewithbappi.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService
{
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("Default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return List.of();
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->  new ResourceNotFoundException("Category","Category",categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDto> dtos = posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(p->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return List.of();
    }
}
