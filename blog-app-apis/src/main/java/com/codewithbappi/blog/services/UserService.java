package com.codewithbappi.blog.services;

import com.codewithbappi.blog.payloads.UserDto;

import java.util.List;

public interface UserService
{
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);


}
