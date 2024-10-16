package com.codewithbappi.blog.repositories;

import com.codewithbappi.blog.entities.Category;
import com.codewithbappi.blog.entities.Posts;
import com.codewithbappi.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Posts,Integer>
{
    List<Posts> findByUser(User user);
    List<Posts> findByCategory(Category category);
    List<Posts> findByTitleContaining(String title);
}
