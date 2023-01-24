package com.rest.books.bootrestbooks.Payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String token;
    private String error;
}
