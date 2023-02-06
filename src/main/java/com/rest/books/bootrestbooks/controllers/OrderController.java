package com.rest.books.bootrestbooks.controllers;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Entities.Orders;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.OrdersDto;
import com.rest.books.bootrestbooks.Payloads.RequestOrderDto;
import com.rest.books.bootrestbooks.Payloads.ResponseOrderDto;
import com.rest.books.bootrestbooks.Repositories.CustomerRepo;
import com.rest.books.bootrestbooks.Services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;


    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderDetails(@PathVariable Integer orderId) {
        Orders orderDetails = this.orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseOrderDto> placeOrder(@RequestBody RequestOrderDto requestOrderDto, @RequestAttribute("loginUser") Customer loginUser) {

        float amount = this.orderService.getCartAmount(requestOrderDto.getCartItems());
        Customer customer = this.customerRepo.findById(loginUser.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId",requestOrderDto.getCustomerId()));

        ResponseOrderDto responseOrderDto = new ResponseOrderDto();

        OrdersDto ordersDto = new OrdersDto(customer, requestOrderDto.getCartItems());
        Orders orders = this.modelMapper.map(ordersDto, Orders.class);
        Orders order = orderService.saveOrder(orders);
        System.out.println("order processed successfully");


        responseOrderDto.setAmount(amount);
        //responseOrderDto.setDate(Date.);
        responseOrderDto.setInvoiceNumber(new Random().nextInt(1000));
        responseOrderDto.setOrderId(orders.getId());
        return new ResponseEntity<>(responseOrderDto, HttpStatus.OK);
    }
}
