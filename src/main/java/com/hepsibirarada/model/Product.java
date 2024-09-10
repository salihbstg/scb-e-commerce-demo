package com.hepsibirarada.model;

import com.hepsibirarada.service.ProductService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", initialValue = 1010165123, allocationSize = 1857)
    private Long id;

    private String productName;
    private String productDescription;
    private String productColor;
    private int productStock;
    private BigDecimal productPrice;
    private LocalDate addedDate;

    public Product(){
        addedDate= LocalDate.now();
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
