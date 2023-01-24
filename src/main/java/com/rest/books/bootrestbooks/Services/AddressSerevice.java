package com.rest.books.bootrestbooks.Services;

import com.rest.books.bootrestbooks.Payloads.AddressDtoRequest;
import com.rest.books.bootrestbooks.Payloads.AddressDtoResponse;

import java.util.List;

public interface AddressSerevice {

   AddressDtoResponse addAddress(AddressDtoRequest address);

   void deleteAddress(Integer addressId);

  AddressDtoResponse updateAddress(AddressDtoRequest address, Integer addressId);

  List<AddressDtoResponse> getAddressesOfCustomer(Integer customerId);
}
