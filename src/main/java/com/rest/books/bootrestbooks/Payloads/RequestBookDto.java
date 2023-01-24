package com.rest.books.bootrestbooks.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookDto {

    private int bookId;

    @NotEmpty(message="Name of the book can't be empty!")
    @Size(min=4, message="CategoryTitle must have minimum of 4 letters")
    private String title;

    @NotEmpty(message="Please provide the language in which book is written!")
    private String language;

    @NotNull(message="please provide total no. of pages of book")
    private int totalPages;

    private String Description;

    @NotNull(message="Binding of book...PaperBack or HardCover?")
    private String coverType;

    @NotNull(message = "Date of publish is required!")
    private Integer publishDate;

    @NotNull(message="Price can't be empty")
    private Integer price;

    private int availableQuantity;

    private Float rating;
    private String imageName;


    private int authorId;
    private int categoryId;

}
