package com.codewithbappi.blog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();


}
