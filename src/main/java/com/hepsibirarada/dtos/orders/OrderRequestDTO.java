package com.hepsibirarada.dtos.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDTO {
    private List<Long> productIdList;
    private Long customerId;
}
