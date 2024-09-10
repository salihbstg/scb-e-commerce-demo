package com.hepsibirarada.repository;

import com.hepsibirarada.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Set<Product> findByCategories_Id(Long id);

    List<Product> findByProductPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    List<Product> findByCategories_IdAndProductPriceBetween(Long categoryId,BigDecimal startPrice,BigDecimal endPrice);
}
