package com.rest.books.bootrestbooks.controllers;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Exceptions.ApiResponse;
import com.rest.books.bootrestbooks.Payloads.AddToCartDto;
import com.rest.books.bootrestbooks.Payloads.CartDto;
import com.rest.books.bootrestbooks.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/customer/{customerId}/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddToCartDto addToCartDto, @PathVariable Integer customerId){ // Wrong hai Customer.   response. getUser lao from Authentication JWT
        this.cartService.addItems(addToCartDto,customerId);

        return new ResponseEntity<>(new ApiResponse("Book added Successfully",true), HttpStatus.OK);
    }
//
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CartDto>> getCartItems(@PathVariable Integer customerId){
        List<CartDto> allItems = this.cartService.getAllItems(customerId);
        return  new ResponseEntity<>(allItems,HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/customer/{customerId}")
    public ApiResponse deleteCart(@PathVariable Integer cartId,@PathVariable Integer customerId){
        this.cartService.deleteCartItems(cartId,customerId);
        return new ApiResponse("items deleted successfully",true);
    }
}
