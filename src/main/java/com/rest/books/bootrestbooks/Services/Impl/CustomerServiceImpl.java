package com.rest.books.bootrestbooks.Services.Impl;

import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Entities.Role;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.CustomerDtoRequest;
import com.rest.books.bootrestbooks.Payloads.CustomerDtoResponse;
import com.rest.books.bootrestbooks.Payloads.CustomerPagingRespDto;
import com.rest.books.bootrestbooks.Repositories.CustomerRepo;
import com.rest.books.bootrestbooks.Repositories.RoleRepo;
import com.rest.books.bootrestbooks.Services.CustomerService;
import com.rest.books.bootrestbooks.config.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public CustomerDtoResponse addCustomer(CustomerDtoRequest customerDtoRequest) {
        Customer user = this.modelMapper.map(customerDtoRequest, Customer.class);

        //encoding pass
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // default roles assigning is normal role to new customer.
        Role role = this.roleRepo.findById(AppConstants.ROLE_NORMAL).get();
        user.getRoles().add(role);

        Customer savedUser = this.customerRepo.save(user);
        return this.modelMapper.map(savedUser, CustomerDtoResponse.class);

    }

    @Override
    public CustomerDtoResponse updateCustomer(CustomerDtoRequest customerDtoRequest, Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));

        customer.setName(customerDtoRequest.getName());
        customer.setEmail(customerDtoRequest.getEmail());
        customer.setPassword(customerDtoRequest.getPassword());

        Customer updatedUser = this.customerRepo.save(customer);
        return this.modelMapper.map(updatedUser, CustomerDtoResponse.class);

    }

    @Override
    public void deleteCustomer(Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));
        this.customerRepo.delete(customer);

    }

    @Override
    public CustomerDtoResponse getCustomer(Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));

        return this.modelMapper.map(customer, CustomerDtoResponse.class);

    }

    @Override
    public CustomerPagingRespDto getAllCustomers(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Customer> pageOfCustomers = this.customerRepo.findAll(page);

        List<Customer> allCustomers = pageOfCustomers.getContent();
        List<CustomerDtoResponse> allCustomersDto = allCustomers.stream().map((customers) -> this.modelMapper.map(customers, CustomerDtoResponse.class)).collect(Collectors.toList());
        CustomerPagingRespDto customerPagingRespDto = new CustomerPagingRespDto();
        customerPagingRespDto.setContent(allCustomersDto);
        customerPagingRespDto.setPageNumber(pageOfCustomers.getNumber());
        customerPagingRespDto.setPageSize(pageOfCustomers.getSize());
        customerPagingRespDto.setTotalRecords(pageOfCustomers.getTotalElements());
        customerPagingRespDto.setTotalPages(pageOfCustomers.getTotalPages());
        customerPagingRespDto.setLastPage(pageOfCustomers.isLast());

        return customerPagingRespDto;
    }
}
