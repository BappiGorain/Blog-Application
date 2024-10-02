package com.codewithbappi.blog.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long filedValue;
    String type;

    public ResourceNotFoundException(String resourceName, String fieldName, long filedValue) {
        super(String.format("%s not found with %s : %s", resourceName,fieldName, filedValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.filedValue = filedValue;

    }


    public ResourceNotFoundException(String resourceName, String fieldName, String type) {
        super(String.format("%s not found with %s : %s", resourceName,fieldName, type));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.type = type;

    }

}