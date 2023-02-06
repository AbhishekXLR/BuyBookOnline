package com.rest.books.bootrestbooks.Payloads;

import com.rest.books.bootrestbooks.Entities.Customer;

import java.util.List;

public class OrdersDto {

    private Long Id;

    List<AddToCartDto> cartItems1;


    private Customer customer;

    public OrdersDto(Customer customer, List<AddToCartDto> cartItems1) {
        this.customer = customer;
        this.cartItems1 = cartItems1;
    }
}