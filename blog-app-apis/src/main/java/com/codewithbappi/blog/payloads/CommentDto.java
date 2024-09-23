package com.codewithbappi.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentDto
{
    private Integer id;

    private String content;

}
