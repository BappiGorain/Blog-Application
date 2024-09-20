package com.codewithbappi.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse
{
    private List<CategoryDto> category;
    private Integer PageNumber;
    private Integer pageSize;
    private Long totalElement;
    private Integer totalPage;
    private Boolean last;
}
