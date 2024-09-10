package com.hepsibirarada.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
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
