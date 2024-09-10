package com.hepsibirarada.controller;

import com.hepsibirarada.dtos.CustomerDTO;
import com.hepsibirarada.exception.CustomApplicationException;
import com.hepsibirarada.service.CustomerService;
import lombok.RequiredArgsConstructor;
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
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }
    @GetMapping("/tc/{tc}")
    public ResponseEntity<CustomerDTO> findByTc(@PathVariable(name = "tc") String tc) {
        CustomerDTO customerDTO=customerService.findCustomerByTc(tc);
        if(customerDTO==null){
            throw new CustomApplicationException("Customer not found!");
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable(name = "id") Long id) {
        CustomerDTO customerDTO=customerService.findCustomerById(id);
        if(customerDTO==null){
            throw new CustomApplicationException("Customer not found!");
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<CustomerDTO> findByUsername(@PathVariable(name = "username") String username) {
        CustomerDTO customerDTO=customerService.findCustomerByUsername(username);
        if(customerDTO==null){
            throw new CustomApplicationException("Customer not found!");
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
    //Delete
    @DeleteMapping("{id}")
    public ResponseEntity<CustomerDTO> deleteById(@PathVariable(name = "id") Long id) {
        CustomerDTO customerDTO=customerService.deleteCustomer(id);
        if(customerDTO==null){
            throw new CustomApplicationException("Customer not found!");
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}
