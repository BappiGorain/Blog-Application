package com.codewithbappi.blog.Exceptions;

public class ApiException extends RuntimeException
{
    public ApiException(String errorMessage)
    {
        super(errorMessage);
    }
    public ApiException() {}
}
