package com.codewithbappi.blog.controller;

import com.codewithbappi.blog.payloads.ApiResponse;
import com.codewithbappi.blog.payloads.CategoryDto;
import com.codewithbappi.blog.payloads.CategoryResponse;
import com.codewithbappi.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")  // maps to "/api/category" in the URL
public class categoryController
{

    @Autowired
    private CategoryService categoryService;

    // Create

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    // Update

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") Integer id)
    {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,id);

        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    // Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id)
    {
        this.categoryService.deleteCategory(id);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);

    }

    // Get category by id

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id)
    {
        CategoryDto returnedCategory = this.categoryService.getCategoryById(id);

        return new ResponseEntity<CategoryDto>(returnedCategory, HttpStatus.OK);
    }


    // Get all categories
    @GetMapping("/")
    public ResponseEntity<CategoryResponse> getAllCategory(@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "4", required = false)Integer pageSize)
    {
        CategoryResponse categoryResponse = this.categoryService.getAllCategory(pageNumber,pageSize);

        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }
}
