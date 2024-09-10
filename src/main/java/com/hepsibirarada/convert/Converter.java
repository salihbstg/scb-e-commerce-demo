package com.hepsibirarada.convert;

import com.hepsibirarada.dtos.CategoryDTO;
import com.hepsibirarada.dtos.CustomerDTO;
import com.hepsibirarada.dtos.ProductDTO;
import com.hepsibirarada.dtos.ProductEnrollDTO;
import com.hepsibirarada.dtos.orders.OrderDTO;
import com.hepsibirarada.dtos.orders.OrderProductDTO;
import com.hepsibirarada.model.Category;
import com.hepsibirarada.model.Customer;
import com.hepsibirarada.model.Order;
import com.hepsibirarada.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Converter {

    //Product

    public Product productEnrollDTOToProduct(ProductEnrollDTO productEnrollDTO){
        Product product=new Product();
        product.setProductStock(productEnrollDTO.getProduct().getProductStock());
        product.setProductName(productEnrollDTO.getProduct().getProductName());
        product.setProductColor(productEnrollDTO.getProduct().getProductColor());
        product.setProductDescription(productEnrollDTO.getProduct().getProductDescription());
        product.setId(productEnrollDTO.getProduct().getId());
        product.setProductPrice(productEnrollDTO.getProduct().getProductPrice());
        product.setCategories(productEnrollDTO.getProduct().getCategories());
        return product;
    }

    public List<ProductDTO> productListToProductDTOList(List<Product> products){
        List<ProductDTO> productDTOS=new ArrayList<>();
        for(Product a:products){
            ProductDTO productDTO=new ProductDTO();
            productDTO.setId(a.getId());
            productDTO.setProductColor(a.getProductColor());
            productDTO.setProductName(a.getProductName());
            productDTO.setProductPrice(a.getProductPrice());
            productDTO.setProductStock(a.getProductStock());
            productDTO.setProductDescription(a.getProductDescription());
            productDTOS.add(productDTO);
            for(Category b:a.getCategories()){
                productDTO.getCategories().add(b.getCategoryName());
            }
        }
        return productDTOS;

    }
    public ProductDTO productEnrollDTOToProductDTO(ProductEnrollDTO productEnrollDTO){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setProductName(productEnrollDTO.getProduct().getProductName());
        productDTO.setProductColor(productEnrollDTO.getProduct().getProductColor());
        productDTO.setProductPrice(productEnrollDTO.getProduct().getProductPrice());
        productDTO.setProductDescription(productEnrollDTO.getProduct().getProductDescription());
        productDTO.setProductStock(productEnrollDTO.getProduct().getProductStock());
        productDTO.setId(productEnrollDTO.getProduct().getId());
        return productDTO;
    }

    public ProductDTO productToProductDTO(Product product) {
        ProductDTO productDTO=new ProductDTO();
        Set<String> categories=new HashSet<>();
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductStock(product.getProductStock());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductColor(product.getProductColor());
        productDTO.setProductName(product.getProductName());
        productDTO.setId(product.getId());
        for(Category a:product.getCategories()){
            categories.add(a.getCategoryName());
        }
        productDTO.setCategories(categories);
        return productDTO;
    }


    //Customer

    public Customer customerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer=new Customer();
        customer.setTc(customerDTO.getTc());
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setTelephone(customerDTO.getTelephone());
        customer.setAddress(customerDTO.getAddress());
        customer.setPassword(customerDTO.getPassword());
        customer.setBirthday(customerDTO.getBirthday());
        customer.setUsername(customerDTO.getUsername());
        customer.setSurName(customerDTO.getSurName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }
    public CustomerDTO customerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setTc(customer.getTc());
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setTelephone(customer.getTelephone());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setBirthday(customer.getBirthday());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setSurName(customer.getSurName());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }

    // Category

    public CategoryDTO categoryToCategoryDTO(Category category){
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setCategoryId(category.getId());
        categoryDTO.setCategoryName(category.getCategoryName());
        return categoryDTO;
    }

    public Category categoryDTOToCategory(CategoryDTO categoryDTO){
        Category category=new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setId(categoryDTO.getCategoryId());
        return category;
    }

    //Order

    public OrderDTO orderToOrderDTO(Order order){
        OrderDTO orderDTO = getOrderDTO(order);
        for(Product product:order.getProducts()){
            OrderProductDTO orderProductDTO=new OrderProductDTO();
            orderProductDTO.setOrderProductId(product.getId());
            orderProductDTO.setOrderProductPrice(product.getProductPrice());
            orderProductDTO.setOrderProductName(product.getProductName());
            orderDTO.getOrderProductDTOS().add(orderProductDTO);
        }
        return orderDTO;
    }

    private static OrderDTO getOrderDTO(Order order) {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setCustomerAddress(order.getCustomer().getAddress());
        orderDTO.setCustomerEmail(order.getCustomer().getEmail());
        orderDTO.setCustomerName(order.getCustomer().getName());
        orderDTO.setCustomerTc(order.getCustomer().getTc());
        orderDTO.setCustomerTelephone(order.getCustomer().getTelephone());
        orderDTO.setCustomerSurName(order.getCustomer().getSurName());
        orderDTO.setOrderId(order.getId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setCustomerId(order.getCustomer().getId());
        return orderDTO;
    }

}
