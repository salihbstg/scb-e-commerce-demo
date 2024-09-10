package com.hepsibirarada.controller;

import com.hepsibirarada.dtos.orders.OrderDTO;
import com.hepsibirarada.dtos.orders.OrderRequestDTO;
import com.hepsibirarada.exception.CustomApplicationException;
import com.hepsibirarada.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    //Post
    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(orderService.saveOrder(orderRequestDTO.getProductIdList(), orderRequestDTO.getCustomerId()), HttpStatus.CREATED);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<OrderDTO>> saveAllOrder(@RequestBody List<OrderRequestDTO> orderRequestDTOS){
        return new ResponseEntity<>(orderService.saveAllOrder(orderRequestDTOS),HttpStatus.CREATED);
    }
    @PostMapping("/{id}/update_status")
    public ResponseEntity<List<Boolean>> updateStatus(@PathVariable(name = "id") Long id, @RequestParam(name = "isPaid") Boolean isPaid, @RequestParam(name = "isDelivered") Boolean isDelivered) {
        return new ResponseEntity<>(orderService.setPaidAndDeliveredStatus(id, isPaid, isDelivered), HttpStatus.OK);
    }
    //Get
    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        return new ResponseEntity<>(orderService.findAllOrders(), HttpStatus.OK);
    }
    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<OrderDTO>> findAllByCustomerId(@PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(orderService.findAllOrdersByCustomerId(userId), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findByOrderId(@PathVariable(name = "id") Long id){
        OrderDTO orderDTO=orderService.findOrderByOrderId(id);
        if(orderDTO==null){
            throw new CustomApplicationException("Order not found!");
        }
        return new ResponseEntity<>(orderDTO,HttpStatus.OK);
    }
}
