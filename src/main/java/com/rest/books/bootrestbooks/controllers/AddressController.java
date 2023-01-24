package com.rest.books.bootrestbooks.controllers;


import com.rest.books.bootrestbooks.Exceptions.ApiResponse;
import com.rest.books.bootrestbooks.Payloads.AddressDtoRequest;
import com.rest.books.bootrestbooks.Payloads.AddressDtoResponse;
import com.rest.books.bootrestbooks.Services.AddressSerevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class AddressController {

    @Autowired
    private AddressSerevice addressSerevice;

    @PostMapping("/address/")
    public ResponseEntity<AddressDtoResponse> addressAdd(@Valid @RequestBody AddressDtoRequest address) {
        AddressDtoResponse addressResponse = this.addressSerevice.addAddress(address);

        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<AddressDtoResponse> updateAddress(@Valid @RequestBody AddressDtoRequest addressDtoRequest, @PathVariable Integer addressId) {

        AddressDtoResponse addressDto = this.addressSerevice.updateAddress(addressDtoRequest, addressId);
        return new ResponseEntity<>(addressDto, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/address/{addressId}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer addressId) {
        this.addressSerevice.deleteAddress(addressId);
        return new ResponseEntity<>(new ApiResponse("Address Deleted Successfully !", true),HttpStatus.OK);
    }

    @GetMapping("/{customerId}/address")
    public ResponseEntity<List<AddressDtoResponse>> getAddressesOfUser(@PathVariable int customerId) {
        List<AddressDtoResponse> customerAddress = this.addressSerevice.getAddressesOfCustomer(customerId);
        return new ResponseEntity<>(customerAddress, HttpStatus.OK);
    }

}
