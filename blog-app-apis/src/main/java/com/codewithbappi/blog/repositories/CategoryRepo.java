package com.codewithbappi.blog.repositories;

import com.codewithbappi.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer>
{

}
