package com.hepsibirarada.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO {
    private Long orderProductId;
    private String orderProductName;
    private BigDecimal orderProductPrice;
}
