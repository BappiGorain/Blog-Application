package com.codewithbappi.blog.payloads;

import com.codewithbappi.blog.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto
{
    private Integer id;
    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<Comment> comments = new HashSet<>();

}
