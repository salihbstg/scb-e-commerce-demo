package com.hepsibirarada.repository;

import com.hepsibirarada.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);
    Customer findByTc(String tc);
}
