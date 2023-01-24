package com.rest.books.bootrestbooks.Services.Impl;

import com.rest.books.bootrestbooks.Entities.Author;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.AuthorDto;
import com.rest.books.bootrestbooks.Payloads.AuthorPagingRespDto;
import com.rest.books.bootrestbooks.Payloads.AuthorRespDto;
import com.rest.books.bootrestbooks.Repositories.AuthorRepo;
import com.rest.books.bootrestbooks.Services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthorRespDto addAuthor(AuthorDto authorDto) {
        Author author = this.modelMapper.map(authorDto, Author.class);
        Author addAuthor = this.authorRepo.save(author);
        return this.modelMapper.map(addAuthor, AuthorRespDto.class);
    }

    @Override
    public AuthorRespDto getAuthorById(Integer authorId) {
        Author author = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "authorId", authorId));
        return this.modelMapper.map(author, AuthorRespDto.class);

    }

    @Override
    public AuthorPagingRespDto getAllAuthor(Integer pageNumber, Integer pageSize, String sortBy) {

         Pageable p= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page<Author> pageOfAuthors = this.authorRepo.findAll(p);

        List<Author> allAuthors = pageOfAuthors.getContent();


        List<AuthorRespDto> authorsList = allAuthors.stream().map((authors) -> this.modelMapper.map(authors, AuthorRespDto.class)).collect(Collectors.toList());

        AuthorPagingRespDto authorPagingRespDto = new AuthorPagingRespDto();
        authorPagingRespDto.setContent(authorsList);
        authorPagingRespDto.setPageNumber(pageOfAuthors.getNumber());
        authorPagingRespDto.setPageSize(pageOfAuthors.getSize());
        authorPagingRespDto.setTotalRecords(pageOfAuthors.getTotalElements());
        authorPagingRespDto.setTotalPages(pageOfAuthors.getTotalPages());
        authorPagingRespDto.setLastPage(pageOfAuthors.isLast() );

        return authorPagingRespDto;
    }

    @Override
    public AuthorRespDto updateAuthor(AuthorDto authorDto, Integer authorId) {
        Author author = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "authorId", authorId));
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setLocation(author.getLocation());
        Author updatedUser = this.authorRepo.save(author);
        return this.modelMapper.map(updatedUser, AuthorRespDto.class);

    }

    @Override
    public void deleteAuthor(Integer authorId) {
        Author author = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("author", "authorId", authorId));
        this.authorRepo.delete(author);
    }
}
