package com.codewithbappi.blog.repositories;

import com.codewithbappi.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer>
{
}
