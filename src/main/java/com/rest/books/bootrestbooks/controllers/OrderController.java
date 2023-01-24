package com.rest.books.bootrestbooks.controllers;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Entities.Orders;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.OrdersDto;
import com.rest.books.bootrestbooks.Payloads.RequestOrderDto;
import com.rest.books.bootrestbooks.Payloads.ResponseOrderDto;
import com.rest.books.bootrestbooks.Repositories.CustomerRepo;
import com.rest.books.bootrestbooks.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Random;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepo customerRepo;


    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderDetails(@PathVariable Integer orderId) {
        Orders orderDetails = this.orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDto> placeOrder(@RequestBody RequestOrderDto requestOrderDto) {

        float amount = this.orderService.getCartAmount(requestOrderDto.getCartItems());
       // Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));
        Customer customer = this.customerRepo.findById(requestOrderDto.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId",requestOrderDto.getCustomerId()));

        ResponseOrderDto responseOrderDto = new ResponseOrderDto();

        Orders orders = new Orders(customer, requestOrderDto.getCartItems());
        orders = orderService.saveOrder(orders);
        System.out.println("order processed successfully");


        responseOrderDto.setAmount(amount);
       // responseOrderDto.setDate(Date.);
        responseOrderDto.setInvoiceNumber(new Random().nextInt(1000));
        responseOrderDto.setOrderId(orders.getId());
        return new ResponseEntity<>(responseOrderDto, HttpStatus.OK);
    }
}
