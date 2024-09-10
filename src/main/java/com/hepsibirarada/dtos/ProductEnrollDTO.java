package com.hepsibirarada.dtos;

import com.hepsibirarada.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEnrollDTO {
    Set<Long> categoryId=new HashSet<>();
    Product product;
}
