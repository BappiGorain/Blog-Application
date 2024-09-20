package com.codewithbappi.blog.impl;

import com.codewithbappi.blog.Exceptions.ResourceNotFoundException;
import com.codewithbappi.blog.entities.Category;
import com.codewithbappi.blog.entities.Post;
import com.codewithbappi.blog.entities.User;
import com.codewithbappi.blog.payloads.PostDto;
import com.codewithbappi.blog.payloads.PostResponse;
import com.codewithbappi.blog.repositories.CategoryRepo;
import com.codewithbappi.blog.repositories.PostRepo;
import com.codewithbappi.blog.repositories.UserRepo;
import com.codewithbappi.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PostDto updatePost(PostDto postDto, Integer postId)
    {
        Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "found", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId)
    {
        Post post =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "found", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize)
    {

        Pageable p = PageRequest.of(pageNumber, pageSize);

         Page<Post> pagePost = this.postRepo.findAll(p);
         List<Post> posts = pagePost.getContent();

        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        return this.modelMapper.map(post,PostDto.class);
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
    public List<PostDto> searchPosts(String keyword)
    {
        return List.of();
    }



}
