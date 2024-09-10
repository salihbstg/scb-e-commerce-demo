package com.hepsibirarada.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_sequence", initialValue = 1010165123, allocationSize = 1231)
    private Long id;

    @Column(unique = true, nullable = false)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
