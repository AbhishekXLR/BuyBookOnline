package com.rest.books.bootrestbooks.Payloads;

import com.rest.books.bootrestbooks.Entities.Book;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class AuthorDto {


    @NotEmpty(message ="First name can't be empty!")
    protected String firstName;

    protected String lastName;

    @NotEmpty(message ="location can't be empty!")
    private String location;

   // protected List<Book> book= new ArrayList<>();
}
