package com.rest.books.bootrestbooks.Services;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Payloads.CustomerDtoRequest;
import com.rest.books.bootrestbooks.Payloads.CustomerDtoResponse;
import com.rest.books.bootrestbooks.Payloads.CustomerPagingRespDto;

import java.util.List;

public interface CustomerService {

    CustomerDtoResponse addCustomer(CustomerDtoRequest customerDtoRequest);

    CustomerDtoResponse updateCustomer(CustomerDtoRequest customerDtoRequest,Integer customerId);

    void deleteCustomer(Integer customerId);

    CustomerDtoResponse getCustomer(Integer customerId);

     CustomerPagingRespDto getAllCustomers(Integer pageNumber,Integer pageSize,String sortBy);
}
