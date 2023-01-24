package com.rest.books.bootrestbooks.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;  // we took email as username
    private String password;

}
