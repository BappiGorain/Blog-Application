package com.codewithbappi.blog.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
//import org.springframework.data.annotation.Id;

@Data
@Entity
public class Role
{
    @Id
    private int id;
    private String name;



}
