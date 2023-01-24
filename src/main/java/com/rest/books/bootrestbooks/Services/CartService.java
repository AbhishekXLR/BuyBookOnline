package com.rest.books.bootrestbooks.Services;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Payloads.AddToCartDto;
import com.rest.books.bootrestbooks.Payloads.CartDto;

import java.util.List;


public interface CartService {

    void addItems(AddToCartDto addToCartDto, Integer customerId);

    List<CartDto> getAllItems(Integer customerId);

    void deleteCartItems(Integer cartId,Integer customerId);

}
