package com.rest.books.bootrestbooks.Exceptions;

import lombok.Getter;
import lombok.Setter;


public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
        super(String.format("%s not found with %s : %d", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);


    }
}
