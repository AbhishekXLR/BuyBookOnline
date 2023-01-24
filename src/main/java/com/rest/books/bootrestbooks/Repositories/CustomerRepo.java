package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByEmail(String email);


}
