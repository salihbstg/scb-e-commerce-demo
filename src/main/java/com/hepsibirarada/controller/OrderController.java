package com.hepsibirarada.controller;

import com.hepsibirarada.dtos.orders.OrderDTO;
import com.hepsibirarada.dtos.orders.OrderRequestDTO;
import com.hepsibirarada.model.Order;
import com.hepsibirarada.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<OrderDTO>> findAllByCustomerId(@PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(orderService.findAllByCustomerId(userId), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findByOrderId(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(orderService.findByOrderId(id),HttpStatus.OK);
    }
}
