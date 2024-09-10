package com.hepsibirarada.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private String productDescription;
    private String productColor;
    private int productStock;
    private BigDecimal productPrice;
    Set<String> categories=new HashSet<>();
}
