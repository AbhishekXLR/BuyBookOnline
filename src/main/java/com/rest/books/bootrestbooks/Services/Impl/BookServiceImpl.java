package com.rest.books.bootrestbooks.Services.Impl;

import com.rest.books.bootrestbooks.Entities.Author;
import com.rest.books.bootrestbooks.Entities.Book;
import com.rest.books.bootrestbooks.Entities.Category;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.BookPagingRespDto;
import com.rest.books.bootrestbooks.Payloads.RequestBookDto;
import com.rest.books.bootrestbooks.Payloads.ResponseBookDto;
import com.rest.books.bootrestbooks.Repositories.AuthorRepo;
import com.rest.books.bootrestbooks.Repositories.BookRepository;
import com.rest.books.bootrestbooks.Repositories.CategoryRepo;
import com.rest.books.bootrestbooks.Services.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BooksService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    // adding a Book
    public ResponseBookDto addBook(RequestBookDto requestBookDto) {
        Author author = this.authorRepo.findById(requestBookDto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Author", "AuthorId", requestBookDto.getAuthorId()));
        Category category = this.categoryRepo.findById(requestBookDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", requestBookDto.getCategoryId()));

        Book book = this.modelMapper.map(requestBookDto, Book.class);
        book.setAuthor(author);
        book.setCategory(category);


        Book result = this.bookRepository.save(book);
        ResponseBookDto respBook = this.modelMapper.map(result, ResponseBookDto.class);
        respBook.setFirstName(author.getFirstName());
        return respBook;

    }

    public BookPagingRespDto getAllBooks(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Book> pageOfBooks = this.bookRepository.findAll(p);
        List<Book> allBook = pageOfBooks.getContent();

        List<ResponseBookDto> bookList = allBook.stream().map((book) -> this.modelMapper.map(book, ResponseBookDto.class)).collect(Collectors.toList());

        BookPagingRespDto bookPagingRespDto = new BookPagingRespDto();

        bookPagingRespDto.setContent(bookList);
        bookPagingRespDto.setPageNumber(pageOfBooks.getNumber());
        bookPagingRespDto.setPageSize(pageOfBooks.getSize());
        bookPagingRespDto.setTotalRecords(pageOfBooks.getTotalElements());
        bookPagingRespDto.setTotalPages(pageOfBooks.getTotalPages());
        bookPagingRespDto.setLastPage(pageOfBooks.isLast());
        return bookPagingRespDto;
    }

    /*Getting a book by Id*/
    public ResponseBookDto getBookById(Integer id) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "BookId", id));
        ResponseBookDto responseBookDto = this.modelMapper.map(book, ResponseBookDto.class);
        return responseBookDto;
    }


    // Deleting  a Book
    public void deleteBook(Integer id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", id));
        bookRepository.delete(book);
    }

    //Updating A book
    public ResponseBookDto updateBook(RequestBookDto requestBookDto, Integer bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "bookId", bookId));

        book.setTitle(requestBookDto.getTitle());
        book.setLanguage(requestBookDto.getLanguage());
        book.setDescription(requestBookDto.getDescription());
        book.setTotalPages(requestBookDto.getTotalPages());
        book.setPublishDate(requestBookDto.getPublishDate());
        book.setPrice(requestBookDto.getPrice());
        book.setCoverType(requestBookDto.getCoverType());
        book.setRating(requestBookDto.getRating());
        book.setImageName(requestBookDto.getImageName());
        book.setAvailableQuantity(requestBookDto.getAvailableQuantity());
        // book.setAuthor();


        Book updatedBook = this.bookRepository.save(book);
        return this.modelMapper.map(updatedBook, ResponseBookDto.class);

    }

    // getting books by a particular author
    @Override
    public BookPagingRespDto getBooksByAuthor(Integer authorId, Integer pageNumber, Integer pageSize, String sortBy) {
        Author author = this.authorRepo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author", "authorId", authorId));

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<Book> booksPage = bookRepository.findByAuthor(author, page);
        List<Book> authorBooks = booksPage.getContent();

        List<ResponseBookDto> authorBooksList = authorBooks.stream().map((books) -> this.modelMapper.map(books, ResponseBookDto.class)).collect(Collectors.toList());

        BookPagingRespDto bookPagingRespDto = new BookPagingRespDto();
        bookPagingRespDto.setContent(authorBooksList);
        bookPagingRespDto.setPageNumber(booksPage.getNumber());
        bookPagingRespDto.setPageSize(booksPage.getSize());
        bookPagingRespDto.setTotalRecords(booksPage.getTotalElements());
        bookPagingRespDto.setTotalPages(booksPage.getTotalPages());
        bookPagingRespDto.setLastPage(booksPage.isLast());
        return bookPagingRespDto;
    }

    @Override
    public BookPagingRespDto getBooksByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Slice<Book> pageBook = this.bookRepository.findByCategory(category, page);
        List<Book> booksByCat = pageBook.getContent();

        List<ResponseBookDto> catBookList = booksByCat.stream().map((books) -> this.modelMapper.map(books, ResponseBookDto.class)).collect(Collectors.toList());

        BookPagingRespDto bookPagingRespDto = new BookPagingRespDto();
        bookPagingRespDto.setContent(catBookList);
        bookPagingRespDto.setPageNumber(pageBook.getNumber());
        bookPagingRespDto.setPageSize(pageBook.getSize());
        // bookPagingRespDto.setTotalRecords(books.get);
        //bookPagingRespDto.setTotalPages(books.get);
        bookPagingRespDto.setLastPage(pageBook.isLast());
        return bookPagingRespDto;
    }

    @Override
    public List<ResponseBookDto> searchBooks(String keyword) {
        List<Book> books = this.bookRepository.findByTitleContaining(keyword);
        List<ResponseBookDto> bookDtos = books.stream().map((book) -> this.modelMapper.map(book, ResponseBookDto.class)).collect(Collectors.toList());

        return bookDtos;

    }

}
