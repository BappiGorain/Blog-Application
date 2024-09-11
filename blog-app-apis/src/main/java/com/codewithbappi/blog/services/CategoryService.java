package com.codewithbappi.blog.services;

import com.codewithbappi.blog.entities.Category;
import com.codewithbappi.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService
{
    // Create
    CategoryDto createCategory(CategoryDto categoryDto);

    // update
     CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    // get by id
    CategoryDto getCategoryById(Integer categoryId);


    // get all
    List<CategoryDto> getAllCategory();

}
