package com.codewithbappi.blog.services;

import com.codewithbappi.blog.payloads.CategoryDto;
import com.codewithbappi.blog.payloads.CategoryResponse;

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
    CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize);

}
