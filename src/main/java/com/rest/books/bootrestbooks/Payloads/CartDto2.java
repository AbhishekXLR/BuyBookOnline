package com.rest.books.bootrestbooks.Payloads;


import lombok.Data;

@Data
public class CartDto2 {

    private Integer quantity;
   // private ResponseBookDto book;
    private double totalCost;
}
