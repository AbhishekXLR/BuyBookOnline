package com.rest.books.bootrestbooks.Repositories;

import com.rest.books.bootrestbooks.Entities.Address;
import com.rest.books.bootrestbooks.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address,Integer> {

    List<Address> findByCustomer(Customer customer);
}
