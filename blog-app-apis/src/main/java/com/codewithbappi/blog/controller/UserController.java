package com.codewithbappi.blog.controller;

import com.codewithbappi.blog.payloads.ApiResponse;
import com.codewithbappi.blog.payloads.UserDto;
import com.codewithbappi.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Post -> Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }


    // Put -> Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId)
    {
        UserDto updatedUser = this.userService.updateUser(userDto,userId);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
    }

    // Get -> Get user

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>  getUserById(@PathVariable("userId") Integer id)
    {
         UserDto returnedUser = this.userService.getUserById(id);

         return new ResponseEntity<UserDto>(returnedUser,HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>>  getAllUsers()
    {
        List<UserDto> usersDto = this.userService.getAllUsers();

        return new ResponseEntity<List<UserDto>>(usersDto,HttpStatus.OK);
    }

    // Delete -> Delete user

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer id)
    {
        this.userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted successfully",true),HttpStatus.OK);
    }


}
