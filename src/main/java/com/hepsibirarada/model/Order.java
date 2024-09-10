package com.hepsibirarada.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    public Order() {
        this.orderDate = LocalDate.now();
        this.isDelivered = false;
        this.isPaid = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", initialValue = 1010165123, allocationSize = 1231)
    private Long id;

    private LocalDate orderDate;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    private BigDecimal totalPrice = BigDecimal.ZERO;

    public void calculateTotalPrice() {
        for (Product a : this.products) {
            totalPrice = totalPrice.add(a.getProductPrice());
        }
    }

    private Boolean isPaid;
    private Boolean isDelivered;

}
