package com.rest.books.bootrestbooks.Payloads;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrderDto {

    private long orderId;
    private float amount;
    private int invoiceNumber;
    private Date Date;

}
