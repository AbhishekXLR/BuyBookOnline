package com.rest.books.bootrestbooks.Services;

import com.rest.books.bootrestbooks.Entities.Cart;
import com.rest.books.bootrestbooks.Entities.Orders;
import com.rest.books.bootrestbooks.Payloads.AddToCartDto;

import java.util.List;

public interface OrderService {

    Orders getOrderDetails(int orderId);

    float getCartAmount(List<AddToCartDto> cartList);

    Orders saveOrder(Orders order);
}
