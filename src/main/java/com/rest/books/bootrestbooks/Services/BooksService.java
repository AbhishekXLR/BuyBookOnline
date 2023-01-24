package com.rest.books.bootrestbooks.Services;

import com.rest.books.bootrestbooks.Payloads.BookPagingRespDto;
import com.rest.books.bootrestbooks.Payloads.RequestBookDto;
import com.rest.books.bootrestbooks.Payloads.ResponseBookDto;

import java.util.List;

public interface BooksService {

    BookPagingRespDto getAllBooks(Integer pageNumber, Integer pageSize, String sortBy);

    ResponseBookDto getBookById(Integer id);

    ResponseBookDto addBook(RequestBookDto requestBookDto);

    void deleteBook(Integer id);

    ResponseBookDto updateBook(RequestBookDto book, Integer id);


    BookPagingRespDto getBooksByAuthor(Integer authorId,Integer pageNumber, Integer pageSize, String sortBy);

    BookPagingRespDto getBooksByCategory(Integer categoryId,Integer pageNumber, Integer pageSize, String sortBy);


    // search book
    List<ResponseBookDto> searchBooks(String keyword);
}

