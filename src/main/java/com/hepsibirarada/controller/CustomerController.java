package com.hepsibirarada.controller;

import com.hepsibirarada.dtos.CustomerDTO;
import com.hepsibirarada.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    //Post
    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.saveCustomer(customerDTO), HttpStatus.CREATED);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<CustomerDTO>> saveAllCustomers(@RequestBody List<CustomerDTO> customerDTOS) {
        return new ResponseEntity<>(customerService.saveAllCustomer(customerDTOS), HttpStatus.OK);
    }
    //Get
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAllCustomer() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/tc/{tc}")
    public ResponseEntity<CustomerDTO> findByTc(@PathVariable(name = "tc") String tc) {
        return new ResponseEntity<>(customerService.findByTc(tc), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<CustomerDTO> findByUsername(@PathVariable(name = "username") String username) {
        return new ResponseEntity<>(customerService.findByUsername(username), HttpStatus.OK);
    }
    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<CustomerDTO> deleteById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }
}
