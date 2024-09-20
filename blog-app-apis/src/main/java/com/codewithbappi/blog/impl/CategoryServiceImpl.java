package com.codewithbappi.blog.impl;

import com.codewithbappi.blog.Exceptions.ResourceNotFoundException;
import com.codewithbappi.blog.entities.Category;
import com.codewithbappi.blog.payloads.CategoryDto;
import com.codewithbappi.blog.payloads.CategoryResponse;
import com.codewithbappi.blog.repositories.CategoryRepo;
import com.codewithbappi.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto)
    {
        Category cat = this.modelMapper.map(categoryDto,Category.class);
        Category savedCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId)
    {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","ID",categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId)
    {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));

        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId)
    {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",categoryId));

        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize)
    {

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<Category> categoryPage = this.categoryRepo.findAll(p);
        List<Category> categories = categoryPage.getContent();

        List<CategoryDto>  categoryDos = categories.stream().map((category) -> this.modelMapper.map(category,CategoryDto.class)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setCategory(categoryDos);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPage(categoryPage.getTotalPages());
        categoryResponse.setTotalElement(categoryPage.getTotalElements());
        categoryResponse.setLast(categoryPage.isLast());

        return categoryResponse;
    }
}
