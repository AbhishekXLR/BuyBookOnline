package com.rest.books.bootrestbooks.controllers;

import com.rest.books.bootrestbooks.Exceptions.ApiResponse;
import com.rest.books.bootrestbooks.Payloads.AuthorDto;
import com.rest.books.bootrestbooks.Payloads.AuthorPagingRespDto;
import com.rest.books.bootrestbooks.Payloads.AuthorRespDto;
import com.rest.books.bootrestbooks.Services.AuthorService;
import com.rest.books.bootrestbooks.config.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/authors/")
    public ResponseEntity<AuthorRespDto> addAuthor(@Valid @RequestBody AuthorDto authorDto){
        AuthorRespDto author= this.authorService.addAuthor(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }


    @GetMapping("/authors/{authorId}")
    public ResponseEntity<AuthorRespDto> getAuthor(@PathVariable Integer authorId){
        AuthorRespDto author = this.authorService.getAuthorById(authorId);
        return  new ResponseEntity<>(author,HttpStatus.ACCEPTED);
    }


    @GetMapping("/authors/")
    public ResponseEntity<AuthorPagingRespDto> getAllAuthor(@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
                                                            @RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                            @RequestParam(value="sortBy",defaultValue = "authorId",required = false) String sortBy){
        AuthorPagingRespDto allAuthor = this.authorService.getAllAuthor(pageNumber,pageSize,sortBy);


        return new ResponseEntity<>(allAuthor,HttpStatus.OK);
    }

    @PutMapping("/authors/{authorId}")
    public ResponseEntity<AuthorRespDto> updateAuthor(@Valid @RequestBody AuthorDto authorDto,@PathVariable Integer authorId){
        AuthorRespDto authorUpdate = this.authorService.updateAuthor(authorDto, authorId);
        return new ResponseEntity<>(authorUpdate,HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping ("/authors/{authorId}")
    public ResponseEntity<ApiResponse> deleteAuthor(@PathVariable Integer authorId){
        this.authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(new ApiResponse("Author deleted Successfully",true),HttpStatus.OK);
    }

}
