package com.rest.books.bootrestbooks.Services.Impl;

import com.rest.books.bootrestbooks.Entities.Address;
import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.AddressDtoRequest;
import com.rest.books.bootrestbooks.Payloads.AddressDtoResponse;
import com.rest.books.bootrestbooks.Repositories.AddressRepo;
import com.rest.books.bootrestbooks.Repositories.CustomerRepo;
import com.rest.books.bootrestbooks.Services.AddressSerevice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressSerevice {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AddressDtoResponse addAddress(AddressDtoRequest userAddress) {
        Customer customer = this.customerRepo.findById(userAddress.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", userAddress.getCustomerId()));

        Address address = this.modelMapper.map(userAddress, Address.class);
        address.setCustomer(customer);

        Address savedAddress = this.addressRepo.save(address);
        return this.modelMapper.map(savedAddress, AddressDtoResponse.class);

    }

    @Override
    public void deleteAddress(Integer addressId) {

        Address address = this.addressRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("address", "addressId", addressId));
        this.addressRepo.delete(address);

    }

    @Override
    public AddressDtoResponse updateAddress(AddressDtoRequest address, Integer addressId) {
        Address address1 = this.addressRepo.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("address", "addressId", addressId));

        address1.setDetailedAddress(address.getDetailedAddress());
        address1.setCity(address.getCity());
        address1.setLandmark(address.getLandmark());
        address1.setState(address.getState());
        address1.setPinCode(address.getPinCode());
        address1.setContactNumber1(address.getContactNumber1());
        address1.setContactNumber2(address.getContactNumber2());

        Address savedAdd = this.addressRepo.save(address1);

        return this.modelMapper.map(savedAdd, AddressDtoResponse.class);

    }

    @Override
    public List<AddressDtoResponse> getAddressesOfCustomer(Integer customerId) {

        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));
        List<Address> addressOfCustomer = this.addressRepo.findByCustomer(customer);

        List<AddressDtoResponse> addresses = addressOfCustomer.stream().map((address) -> this.modelMapper.map(address, AddressDtoResponse.class)).collect(Collectors.toList());
        return addresses;
    }
}
