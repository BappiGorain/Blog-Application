package com.codewithbappi.blog.impl;

import com.codewithbappi.blog.Exceptions.ResourceNotFoundException;
import com.codewithbappi.blog.config.AppConstants;
import com.codewithbappi.blog.entities.User;
import com.codewithbappi.blog.payloads.UserDto;
import com.codewithbappi.blog.payloads.UserResponse;
import com.codewithbappi.blog.repositories.RoleRepo;
import com.codewithbappi.blog.repositories.UserRepo;
import com.codewithbappi.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codewithbappi.blog.entities.Role;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto,User.class);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //roles
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = this.userRepo.save(user);

        return this.modelMapper.map(newUser,UserDto.class);

    }

    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer userId)
    {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User "," Id ",id));
        return this.userToDto(user);
}

    @Override
    public UserResponse getAllUsers(Integer pageNumber, Integer pageSize)
    {
        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<User> userPage = this.userRepo.findAll(p);
        List<User> users = userPage.getContent();

        List<UserDto> userDtos = users.stream().map((user)->this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        
        userResponse.setUsers(userDtos);
        userResponse.setPageNumber(userPage.getNumber());
        userResponse.setPageSize(userPage.getSize());
        userResponse.setTotalPages(userPage.getTotalPages());
        userResponse.setTotalElements(userPage.getTotalElements());
        userResponse.setLastPage(userPage.isLast());
        
        return userResponse;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User "," Id ",userId));
        this.userRepo.delete(user);
    }


    public User dtoToUser(UserDto userDto)
    {
        User user = this.modelMapper.map(userDto,User.class);

//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user)
    {

        UserDto userDto = this.modelMapper.map(user,UserDto.class);

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }

}
