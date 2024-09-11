package com.codewithbappi.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto
{
    private Integer categoryId;
    private String categoryTitle;
    private  String categoryDescription;
}
