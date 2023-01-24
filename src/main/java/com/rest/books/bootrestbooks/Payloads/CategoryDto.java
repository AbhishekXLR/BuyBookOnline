package com.rest.books.bootrestbooks.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

    private int id;

    @NotEmpty(message="category name can't be empty")
    @Size(min=4, message="CategoryTitle must have minimum of 4 letters")
    private String categoryName;
}
