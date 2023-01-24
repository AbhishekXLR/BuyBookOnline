package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
