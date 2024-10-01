package com.codewithbappi.blog.payloads;

import com.codewithbappi.blog.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto
{
    private int id;

    @NotEmpty
    @Size(min=4,message = "Username must be at least 4 characters long")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3 , max = 10, message = "Password must be at least  3 characters long and less than 10 characters")
    private String password;

    @NotNull
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
