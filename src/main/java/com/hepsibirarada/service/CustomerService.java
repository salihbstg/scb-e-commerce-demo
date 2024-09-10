package com.hepsibirarada.service;

import com.hepsibirarada.convert.Converter;
import com.hepsibirarada.dtos.CustomerDTO;
import com.hepsibirarada.model.Customer;
import com.hepsibirarada.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final Converter converter;
    private final CustomerRepository customerRepository;

    //SAVE
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        customerRepository.save(converter.customerDTOToCustomer(customerDTO));
        return customerDTO;
    }


    public List<CustomerDTO> saveAllCustomer(List<CustomerDTO> customerDTOS) {
        List<Customer> customers = new ArrayList<>();
        for (CustomerDTO customerDTO : customerDTOS) {
            customers.add(converter.customerDTOToCustomer(customerDTO));
        }
        customerRepository.saveAll(customers);
        return customerDTOS;
    }
    //FIND

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();

        for (Customer a : customers) {
            customerDTOS.add(converter.customerToCustomerDTO(a));
        }
        return customerDTOS;
    }

    public CustomerDTO findById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customer -> converter.customerToCustomerDTO(customer)).orElse(null);
    }

    public CustomerDTO findByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null) {
            return converter.customerToCustomerDTO(customer);
        }
        return null;
    }

    public CustomerDTO findByTc(String tc) {
        Customer customer = customerRepository.findByTc(tc);
        if (customer != null) {
            return converter.customerToCustomerDTO(customer);
        }
        return null;
    }

    public CustomerDTO deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.delete(optionalCustomer.get());
            return converter.customerToCustomerDTO(optionalCustomer.get());
        }
        return null;
    }
}
