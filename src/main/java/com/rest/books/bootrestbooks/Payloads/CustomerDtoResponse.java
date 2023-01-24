package com.rest.books.bootrestbooks.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDtoResponse extends CustomerDtoRequest {

    private Integer customerId;

    //private AddressDtoResponse addressDtoResponse;
}
