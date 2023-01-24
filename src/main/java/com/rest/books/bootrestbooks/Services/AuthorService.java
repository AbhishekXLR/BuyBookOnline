package com.rest.books.bootrestbooks.Services;

import com.rest.books.bootrestbooks.Payloads.AuthorDto;
import com.rest.books.bootrestbooks.Payloads.AuthorPagingRespDto;
import com.rest.books.bootrestbooks.Payloads.AuthorRespDto;

public interface AuthorService {

AuthorRespDto addAuthor(AuthorDto authorDto);

AuthorRespDto getAuthorById(Integer authorId);

AuthorPagingRespDto getAllAuthor(Integer pageNumber, Integer pageSize, String sortBy);

AuthorRespDto updateAuthor(AuthorDto authorDto,Integer authorId);

void deleteAuthor(Integer authorId);

}
