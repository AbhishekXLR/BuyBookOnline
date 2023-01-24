package com.rest.books.bootrestbooks.Services.Impl;

import com.rest.books.bootrestbooks.Entities.Book;
import com.rest.books.bootrestbooks.Entities.Cart;
import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.AddToCartDto;
import com.rest.books.bootrestbooks.Payloads.CartDto;
import com.rest.books.bootrestbooks.Payloads.CartDto2;
import com.rest.books.bootrestbooks.Repositories.BookRepository;
import com.rest.books.bootrestbooks.Repositories.CartRepo;
import com.rest.books.bootrestbooks.Repositories.CustomerRepo;
import com.rest.books.bootrestbooks.Services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void addItems(AddToCartDto addToCartDto, Integer customerId) {

        // validating if the product id is valid
        Book book = this.bookRepository.findById(addToCartDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("product", "productId", addToCartDto.getProductId()));
        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));

        Cart cart= this.modelMapper.map(addToCartDto,Cart.class);
        cart.setBook(book);
        cart.setCustomer(customer);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepo.save(cart);
    }

    // Getting all items of cart
    @Override
    public List<CartDto> getAllItems(Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));

        List<Cart> cartList = this.cartRepo.findAllByCustomerOrderByCreatedDateDesc(customer);

        return cartList.stream().map((cart)->this.modelMapper.map(cart, CartDto.class)).collect(Collectors.toList());

    }

    @Override
    public void deleteCartItems(Integer cartId, Integer customerId) {
        Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer", "customerId", customerId));

        Cart cart = this.cartRepo.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("cart", "cartId", cartId));
        this.cartRepo.delete(cart);
    }
}
