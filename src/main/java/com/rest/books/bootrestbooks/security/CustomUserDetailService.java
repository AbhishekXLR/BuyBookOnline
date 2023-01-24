package com.rest.books.bootrestbooks.security;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from dataBase via his username
        Customer customer = this.customerRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User with email :" + username + "not Found!"));

        return customer;  // return Type changed, Because we made User implements UserDetailService
    }
}
