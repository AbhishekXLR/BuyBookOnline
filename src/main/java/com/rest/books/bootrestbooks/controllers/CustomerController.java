package com.rest.books.bootrestbooks.controllers;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Exceptions.ApiResponse;
import com.rest.books.bootrestbooks.Payloads.CustomerDtoRequest;
import com.rest.books.bootrestbooks.Payloads.CustomerDtoResponse;
import com.rest.books.bootrestbooks.Payloads.CustomerPagingRespDto;
import com.rest.books.bootrestbooks.Services.CustomerService;
import com.rest.books.bootrestbooks.config.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDtoResponse> registerUser(@Valid @RequestBody CustomerDtoRequest customerDto) {
        CustomerDtoResponse customer = this.customerService.addCustomer(customerDto);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDtoResponse> updateUser(@RequestBody CustomerDtoResponse customerDto, @PathVariable Integer customerId) {
        CustomerDtoResponse updatedCustomer = this.customerService.updateCustomer(customerDto, customerId);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
    }


    @GetMapping("/")
    public ResponseEntity<CustomerPagingRespDto> getAllUsers(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                             @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                             @RequestParam(value = "sortBy", defaultValue = "customerId", required = false) String sortBy) {
        CustomerPagingRespDto allCustomers = this.customerService.getAllCustomers(pageNumber, pageSize, sortBy);
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDtoResponse> getUser(@PathVariable Integer customerId) {
        CustomerDtoResponse customer = this.customerService.getCustomer(customerId);
        return new ResponseEntity<>(customer, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{customerId}")
    public ApiResponse deleteUser(@PathVariable Integer customerId) {
        this.customerService.deleteCustomer(customerId);
        return new ApiResponse("customer deleted successfully !", true);
    }
}
