package com.rest.books.bootrestbooks.Payloads;


import com.rest.books.bootrestbooks.Entities.Cart;
import jakarta.validation.constraints.NotNull;
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
