package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
}
