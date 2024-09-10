package com.hepsibirarada.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence", initialValue = 1010165123, allocationSize = 1231)
    private Long id;

    private String name;
    private String surName;
    private String tc;
    private String telephone;
    private String address;
    private String birthday;
    private String email;
    private String username;
    private String password;

}
