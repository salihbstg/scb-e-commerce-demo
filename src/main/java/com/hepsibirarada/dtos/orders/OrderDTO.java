package com.hepsibirarada.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;
    private List<OrderProductDTO> orderProductDTOS=new ArrayList<>();
    private Long customerId;
    private String customerName;
    private String customerSurName;
    private String customerTc;
    private String customerAddress;
    private String customerTelephone;
    private String customerEmail;
    private BigDecimal totalPrice;
    private Boolean isDelivered;
    private Boolean isPaid;

}
