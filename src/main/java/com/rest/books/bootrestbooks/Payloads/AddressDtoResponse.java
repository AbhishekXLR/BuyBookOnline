package com.rest.books.bootrestbooks.Payloads;

import com.rest.books.bootrestbooks.Entities.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDtoResponse extends AddressDtoRequest {

    private int addressId;

    private Customer customer;

}
