package com.rest.books.bootrestbooks.Services.Impl;

import com.rest.books.bootrestbooks.Entities.Book;
import com.rest.books.bootrestbooks.Entities.Cart;
import com.rest.books.bootrestbooks.Entities.Customer;
import com.rest.books.bootrestbooks.Entities.Orders;
import com.rest.books.bootrestbooks.Exceptions.ResourceNotFoundException;
import com.rest.books.bootrestbooks.Payloads.AddToCartDto;
import com.rest.books.bootrestbooks.Repositories.BookRepository;
import com.rest.books.bootrestbooks.Repositories.CustomerRepo;
import com.rest.books.bootrestbooks.Repositories.OrderRepo;
import com.rest.books.bootrestbooks.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Orders getOrderDetails(int orderId) {
        Orders order = this.orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order", "orderId", orderId));
        return order;
    }

    @Override
    public float getCartAmount(List<AddToCartDto> cartList) {

        float totalCartAmount = 0f;
        float singleCartAmount = 0f;


        for (AddToCartDto cart : cartList) {
            int availableQuantity = 0;
            int productId = cart.getProductId();
            Optional<Book> book = this.bookRepository.findById(productId);
            if (book.isPresent()) {
                Book book1 = book.get();
                if (book1.getAvailableQuantity() < cart.getQuantity()) {
                    System.out.println("select lesser quantity of book");
                    throw new ResourceNotFoundException("404");

                } else {
                    singleCartAmount = cart.getQuantity() * book1.getPrice();
                    availableQuantity = book1.getAvailableQuantity() - cart.getQuantity();
                }

                totalCartAmount = totalCartAmount + singleCartAmount;
                book1.setAvailableQuantity(availableQuantity);

                cart.setAmount(singleCartAmount);
                cart.setBookName(book1.getTitle());
                bookRepository.save(book1);
            }
        }
        return totalCartAmount;

    }

    public Orders saveOrder(Orders order) {
        return orderRepo.save(order);
    }
}
