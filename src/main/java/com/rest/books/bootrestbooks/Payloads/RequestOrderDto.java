package com.rest.books.bootrestbooks.Payloads;


import lombok.Data;

import java.util.List;

@Data
public class RequestOrderDto {

   // private String bookName;
    private int customerId;
    private String customerEmail;
    private String customerName;
    private List<AddToCartDto> cartItems;
}
