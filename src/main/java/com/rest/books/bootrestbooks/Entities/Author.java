package com.rest.books.bootrestbooks.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Column(unique = true,name = "First_Name",nullable = false,length = 15)
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    private String location;


    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JsonBackReference
    private List<Book> book = new ArrayList<>();


}
