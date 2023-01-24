package com.rest.books.bootrestbooks.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_Id")
    private int bookId;

    @Column(unique = true,name = "Book_name", nullable = false, length = 40)
    private String title;

    @Column(name = "Book_language", nullable = false, length = 10)
    private String language;
    private int totalPages;
    private String Description;
    private String coverType;
    private int publishDate;
    private int availableQuantity;

    @Column(nullable = false)
    private int price;
    private float rating;
    private String imageName;

    @ManyToOne
    private Author author;

    @ManyToOne
    private Category category;

}
