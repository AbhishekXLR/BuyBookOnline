package com.rest.books.bootrestbooks.Payloads;

import com.rest.books.bootrestbooks.Entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDtoRequest {


    @NotEmpty(message = "Name can't be Empty!")
    @Size(min =4,message = "Your name must have minimum of 4 characters !")
    protected String name;

    @Email(message = "please provide a valid email !")
    protected String email;

    @NotEmpty(message ="Password can't be empty")
    @Size(min =5,max =10,message="your password must contain at least 5 characters and maximum of 10 characters")
    private String password;


}
