package com.rest.books.bootrestbooks.Payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddToCartDto {


    private Integer id;
    private @NotNull Integer productId;
    private String bookName;
    private String customerName;
    private @NotNull Integer quantity;
    private float amount;
}
