package com.codewithbappi.blog.services;

import com.codewithbappi.blog.payloads.UserDto;
import com.codewithbappi.blog.payloads.UserResponse;

public interface UserService
{
    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer id);
    UserResponse getAllUsers(Integer pageNumber, Integer pageSize);
    void deleteUser(Integer userId);


}
