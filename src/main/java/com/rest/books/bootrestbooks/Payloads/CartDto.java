package com.rest.books.bootrestbooks.Payloads;


import lombok.Data;

@Data
public class CartDto {

    private Integer id;
    private Integer quantity;
    private ResponseBookDto book;

    //private double totalCost;
}
