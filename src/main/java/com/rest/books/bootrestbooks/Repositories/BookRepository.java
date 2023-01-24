package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Author;
import com.rest.books.bootrestbooks.Entities.Book;
import com.rest.books.bootrestbooks.Entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

   Page<Book> findByAuthor(Author author, Pageable pageable);

   Slice<Book> findByCategory(Category author, Pageable pageable);

   List<Book> findByTitleContaining(String title);

}
