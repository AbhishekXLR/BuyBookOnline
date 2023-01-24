package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Cart;
import com.rest.books.bootrestbooks.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Integer> {

    List<Cart> findAllByCustomerOrderByCreatedDateDesc(Customer customer);
}
