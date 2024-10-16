package com.codewithbappi.blog.impl;

import com.codewithbappi.blog.Exceptions.ResourceNotFoundException;
import com.codewithbappi.blog.entities.Category;
import com.codewithbappi.blog.entities.Posts;
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
import org.springframework.data.domain.Sort;
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
        Posts posts = this.modelMapper.map(postDto, Posts.class);
        posts.setImageName("Default.png");
        posts.setAddedDate(new Date());
        posts.setUser(user);
        posts.setCategory(category);

        Posts newPosts = this.postRepo.save(posts);
        return this.modelMapper.map(newPosts, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId)
    {
        Posts posts =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "found", postId));
        posts.setId(postId);
        posts.setTitle(postDto.getTitle());
        posts.setContent(postDto.getContent());
        posts.setImageName(postDto.getImageName());
        Posts updatedPosts = this.postRepo.save(posts);
        return this.modelMapper.map(updatedPosts, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId)
    {
        Posts posts =  this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "found", postId));
        this.postRepo.delete(posts);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection)
    {

        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("asc"))
        {
            sort = Sort.by(sortBy).ascending();
        }
        else
        {
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
         Page<Posts> pagePost = this.postRepo.findAll(p);
         List<Posts> posts = pagePost.getContent();

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
        Posts posts = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        return this.modelMapper.map(posts,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->  new ResourceNotFoundException("Category","Category",categoryId));
        List<Posts> posts = this.postRepo.findByCategory(cat);
        List<PostDto> dtos = posts.stream().map(post->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        List<Posts> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(p->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword)
    {
        List<Posts> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((p)->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }



}
