package com.rest.books.bootrestbooks.Payloads;

import lombok.Data;

@Data
public class ResponseBookDto {
    private int bookId;


    private String title;

    private String language;

    private int totalPages;

    private String Description;

    private String coverType;

    private Integer publishDate;

    private Integer price;

    private Float rating;

    private String imageName;



    // private AuthorDto author;
   // private CategoryDto category;

    private String authorName;
    private String categoryName;


}

