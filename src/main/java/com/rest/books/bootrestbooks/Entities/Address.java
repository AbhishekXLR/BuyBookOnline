package com.rest.books.bootrestbooks.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(name="address",nullable = false)
    private String detailedAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String landmark;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false,length = 6)
    private int pinCode;

    @Column(name="contact1",nullable = false,length = 13)
    private String contactNumber1;

    private String contactNumber2;


    @JsonManagedReference
    @ManyToOne
    private Customer customer;
}
