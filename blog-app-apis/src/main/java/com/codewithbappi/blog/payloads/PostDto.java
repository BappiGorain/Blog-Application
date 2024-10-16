package com.codewithbappi.blog.payloads;

import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto
{
    private Integer id;
    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();

    @Data
    public static class JwtAuthResponse
    {
        private String token;
    }
}
