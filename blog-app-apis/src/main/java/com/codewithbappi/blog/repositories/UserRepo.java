package com.codewithbappi.blog.repositories;

import com.codewithbappi.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
