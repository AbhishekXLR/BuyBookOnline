package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
