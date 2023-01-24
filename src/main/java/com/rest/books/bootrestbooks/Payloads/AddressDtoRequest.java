package com.rest.books.bootrestbooks.Payloads;

import com.rest.books.bootrestbooks.Entities.Customer;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;

@Data
public class AddressDtoRequest {


    private int addressId;

    @NotEmpty(message = "Address can't be empty")
    protected String detailedAddress;

    @NotEmpty(message = "Please give the City")
    protected String city;

    @NotEmpty(message = "landMark can't be empty")
    private String landmark;

    @NotEmpty(message = "state can't be empty")
    protected String state;

    @NotNull(message = "Invalid PINCode")
   // @Size(min = 6)
    @Min(100000)
    @Max(999999)
    protected int pinCode;

    @NotEmpty(message = "contact can't be empty")
    @Size(min=10, message="Invalid number",max = 13)
    protected String contactNumber1;

    protected String contactNumber2;

    private int customerId;
}
