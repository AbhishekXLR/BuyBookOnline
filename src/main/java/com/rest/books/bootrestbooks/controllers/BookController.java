package com.rest.books.bootrestbooks.controllers;

import com.rest.books.bootrestbooks.Exceptions.ApiResponse;
import com.rest.books.bootrestbooks.Payloads.BookPagingRespDto;
import com.rest.books.bootrestbooks.Payloads.RequestBookDto;
import com.rest.books.bootrestbooks.Payloads.ResponseBookDto;
import com.rest.books.bootrestbooks.Services.BooksService;
import com.rest.books.bootrestbooks.config.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class BookController {

    @Autowired
    private BooksService booksService;


    /* Add NEW Book Handler*/
    @PostMapping("/books")
    public ResponseEntity<ResponseBookDto> addBook(@Valid @RequestBody RequestBookDto book) {
        ResponseBookDto addedBook = this.booksService.addBook(book);
        return new ResponseEntity<>(addedBook, HttpStatus.CREATED);

    }

    @GetMapping("/books")
    public ResponseEntity<BookPagingRespDto> getBooks(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = "bookId", required = false) String sortBy) {
        BookPagingRespDto allBooks = this.booksService.getAllBooks(pageNumber, pageSize, sortBy);

        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    /* Get Single Book Handler */
    @GetMapping("/books/{bookId}")
    public ResponseEntity<ResponseBookDto> getBook(@PathVariable("bookId") int bookId) {
        ResponseBookDto book = booksService.getBookById(bookId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));                                      // gives Object book
    }


    /* Delete Book Handler*/
    @DeleteMapping("/books/{bookId}")
    public ApiResponse deleteAddress(@PathVariable Integer bookId) {
        this.booksService.deleteBook(bookId);
        return new ApiResponse("Book Deleted Successfully !", true);
    }


    /* Update Book Handler*/
    @PutMapping("/books/{bookId}")
    public ResponseEntity<ResponseBookDto> updateBook(@Valid @RequestBody RequestBookDto requestBookDto, @PathVariable("bookId") int bookId) {

        ResponseBookDto updatedBook = this.booksService.updateBook(requestBookDto, bookId);
        return new ResponseEntity<>(updatedBook, HttpStatus.ACCEPTED);
    }

    @GetMapping("/author/{authorId}/books")
    public ResponseEntity<BookPagingRespDto> getBooksByAuthor(@PathVariable Integer authorId,
                                                              @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                              @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                              @RequestParam(value = "sortBy", defaultValue = "bookId", required = false) String sortBy) {
        BookPagingRespDto booksByAuthor = this.booksService.getBooksByAuthor(authorId, pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
    }


    @GetMapping("/category/{categoryId}/books")
    public ResponseEntity<BookPagingRespDto> getBooksCategoryWise(@PathVariable Integer categoryId,
                                                                  @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                  @RequestParam(value = "sortBy", defaultValue = "bookId", required = false) String sortBy) {

        BookPagingRespDto booksByCategory = this.booksService.getBooksByCategory(categoryId, pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(booksByCategory, HttpStatus.OK);
    }

    // search
    @GetMapping("/books/search/{keywords}")
    public ResponseEntity<List<ResponseBookDto>> searchPostByTitle(@PathVariable String keywords) {
        List<ResponseBookDto> results = this.booksService.searchBooks(keywords);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
