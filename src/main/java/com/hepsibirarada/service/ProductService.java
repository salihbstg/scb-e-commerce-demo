package com.hepsibirarada.service;

import com.hepsibirarada.convert.Converter;
import com.hepsibirarada.dtos.ProductDTO;
import com.hepsibirarada.dtos.ProductEnrollDTO;
import com.hepsibirarada.model.Category;
import com.hepsibirarada.model.Product;
import com.hepsibirarada.repository.CategoryRepository;
import com.hepsibirarada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Converter converter;

    //Save
    public void enrollProductInCategory(Product product, Set<Long> categories) {
        for (Long a : categories) {
            Optional<Category> optionalCategory = categoryRepository.findById(a);
            optionalCategory.ifPresent(category -> product.getCategories().add(category));
        }
    }
    public List<ProductDTO> saveAllProducts(List<ProductEnrollDTO> productEnrollDTOS) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEnrollDTO a : productEnrollDTOS) {
            productDTOS.add(saveProduct(a));
        }
        return productDTOS;
    }
    public ProductDTO saveProduct(ProductEnrollDTO productEnrollDTO) {
        enrollProductInCategory(productEnrollDTO.getProduct(), productEnrollDTO.getCategoryId());
        productRepository.save(converter.productEnrollDTOToProduct(productEnrollDTO));
        return converter.productEnrollDTOToProductDTO(productEnrollDTO);
    }
    //FIND
    public List<ProductDTO> findAllProducts() {
        List<Product> product = productRepository.findAll();
        return converter.productListToProductDTOList(product);
    }
    public ProductDTO findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductDTO productDTO = converter.productToProductDTO(optionalProduct.get());
            for (Category a : optionalProduct.get().getCategories()) {
                productDTO.getCategories().add(a.getCategoryName());
            }
            return productDTO;
        }
        return null;
    }
    public Set<ProductDTO> findProductByCategoryId(Long id) {
        Set<Product> products = productRepository.findByCategories_Id(id);
        Set<ProductDTO> productDTOS = new HashSet<>();
        for (Product a : products) {
            productDTOS.add(converter.productToProductDTO(a));
        }
        return productDTOS;
    }
    //DELETE
    public Boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        }
        return false;
    }
    public ProductDTO updateProduct(ProductDTO productDTO) {
        if (productDTO.getId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();

                if (productDTO.getProductName() != null) {
                    product.setProductName(productDTO.getProductName());
                }
                if (productDTO.getProductColor() != null) {
                    product.setProductColor(productDTO.getProductColor());
                }
                if (productDTO.getProductDescription() != null) {
                    product.setProductDescription(productDTO.getProductDescription());
                }
                if (productDTO.getCategories() != null) {
                    Set<Category> categories = new HashSet<>();
                    for (String categoryName : productDTO.getCategories()) {
                        Category category = categoryRepository.findByCategoryName(categoryName);
                        if (category != null) {
                            categories.add(category);
                        }
                    }
                    product.setCategories(categories);
                }
                return converter.productToProductDTO(productRepository.save(product));
            }
        }
        return null;
    }
    public List<ProductDTO> findProductsByPriceBetween(BigDecimal min, BigDecimal max){
        List<Product> products=productRepository.findByProductPriceBetween(min, max);
        return converter.productListToProductDTOList(products);
    }
    public List<ProductDTO> findProductsByCategories_IdAndProductPriceBetween(Long categoryId, BigDecimal startPrice, BigDecimal endPrice){
        List<Product> products=productRepository.findByCategories_IdAndProductPriceBetween(categoryId,startPrice,endPrice);
        return converter.productListToProductDTOList(products);
    }
    public ProductDTO updateProductStock(Long id,int newStock) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            product.setProductStock(newStock);
            productRepository.save(product);
            return converter.productToProductDTO(product);
        }
        return null;
    }
    public ProductDTO decrementProductStockByOne(Long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            if(product.getProductStock()>0){
                product.setProductStock(product.getProductStock()-1);
                productRepository.save(product);
            }
            return converter.productToProductDTO(product);
        }
        return null;
    }
}
