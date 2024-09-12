package com.codewithbappi.blog.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "post_title", nullable = false)
    private String title;
    @Column(name = "post_content", nullable = false)
    private String content;
    @Column(name = "Image_name", nullable = false)
    private String imageName;
    @Column(name = "post_Date", nullable = false)
    private Date addedDate;


    @ManyToOne
    @JoinColumn(name = "Category_id")
    private Category category;

    @ManyToOne
    private User user;


}
