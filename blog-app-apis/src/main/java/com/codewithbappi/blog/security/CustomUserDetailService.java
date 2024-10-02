package com.codewithbappi.blog.security;

import com.codewithbappi.blog.Exceptions.ResourceNotFoundException;
import com.codewithbappi.blog.entities.User;
import com.codewithbappi.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        // Loading user from database by username
        User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","UserName " , username));
        return user;
    }
}
