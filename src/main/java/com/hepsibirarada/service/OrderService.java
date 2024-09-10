package com.hepsibirarada.service;

import com.hepsibirarada.convert.Converter;
import com.hepsibirarada.dtos.orders.OrderDTO;
import com.hepsibirarada.dtos.orders.OrderRequestDTO;
import com.hepsibirarada.model.Customer;
import com.hepsibirarada.model.Order;
import com.hepsibirarada.model.Product;
import com.hepsibirarada.repository.CustomerRepository;
import com.hepsibirarada.repository.OrderRepository;
import com.hepsibirarada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final Converter converter;

    public OrderDTO saveOrder(List<Long> productIdList, Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        List<Product> products = new ArrayList<>();
        Order order = new Order();
        for (Long a : productIdList) {
            Optional<Product> optionalProduct = productRepository.findById(a);
            optionalProduct.ifPresent(products::add);
        }
        if (optionalCustomer.isPresent()) {
            order.setCustomer(optionalCustomer.get());
            order.setProducts(products);
            order.calculateTotalPrice();
        }
        orderRepository.save(order);
        return converter.orderToOrderDTO(order);
    }
    public List<OrderDTO> saveAllOrder(List<OrderRequestDTO> orderRequestDTOS) {
        List<OrderDTO> orderDTOS=new ArrayList<>();
        for (OrderRequestDTO orderRequestDTO : orderRequestDTOS) {
            orderDTOS.add(saveOrder(orderRequestDTO.getProductIdList(),orderRequestDTO.getCustomerId()));
        }
        return orderDTOS;
    }

    public List<Boolean> setPaidAndDeliveredStatus(Long id, Boolean isPaid, Boolean isDelivered) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        List<Boolean> booleans = new ArrayList<>();
        optionalOrder.ifPresent(order -> {
            order.setIsPaid(isPaid);
            order.setIsDelivered(isDelivered);
        });
        booleans.add(isPaid);
        booleans.add(isDelivered);
        return booleans;
    }


    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderDTOS.add(converter.orderToOrderDTO(order));
        }
        return orderDTOS;
    }

    public List<OrderDTO> findAllByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderDTOS.add(converter.orderToOrderDTO(order));
        }
        return orderDTOS;
    }


    public OrderDTO findByOrderId(Long id) {
        Optional<Order> optionalOrder=orderRepository.findById(id);
        return optionalOrder.map(converter::orderToOrderDTO).orElse(null);
    }
}
