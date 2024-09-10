package com.hepsibirarada.controller;

import com.hepsibirarada.dtos.ProductDTO;
import com.hepsibirarada.dtos.ProductEnrollDTO;
import com.hepsibirarada.exception.CustomApplicationException;
import com.hepsibirarada.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //Post
    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductEnrollDTO productEnrollDTO) {
        return new ResponseEntity<>(productService.saveProduct(productEnrollDTO), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductDTO>> saveAllProducts(@RequestBody List<ProductEnrollDTO> productEnrollDTOS) {
        return new ResponseEntity<>(productService.saveAllProducts(productEnrollDTOS), HttpStatus.OK);
    }

    //Get
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable(name = "id") Long id) {
        ProductDTO productDTO=productService.findProductById(id);
        if(productDTO==null){
            throw new CustomApplicationException("Product not found!");
        }
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Set<ProductDTO>> findByCategoryId(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(productService.findProductByCategoryId(id), HttpStatus.OK);
    }

    @GetMapping("/range")
    public ResponseEntity<List<ProductDTO>> findByProductPriceRange(@RequestParam(name = "min") BigDecimal min, @RequestParam(name = "max") BigDecimal max) {
        return new ResponseEntity<>(productService.findProductsByPriceBetween(min, max), HttpStatus.OK);
    }

    @GetMapping("/category/range")
    public ResponseEntity<List<ProductDTO>> findByCategoryAndProductPriceRange(@RequestParam(name = "categoryId") Long categoryId, @RequestParam(name = "min") BigDecimal min, @RequestParam(name = "max") BigDecimal max) {
        return new ResponseEntity<>(productService.findProductsByCategories_IdAndProductPriceBetween(categoryId, min, max), HttpStatus.OK);
    }

    //Update
    @PatchMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(productDTO), HttpStatus.OK);
    }

    @PatchMapping("/stock/{id}")
    public ResponseEntity<ProductDTO> updateProductStock(@PathVariable(name = "id") Long id, @RequestParam(name = "newStock") int newStock) {
        return new ResponseEntity<>(productService.updateProductStock(id, newStock), HttpStatus.OK);
    }

    @PatchMapping("/stock/decrement/{id}")
    public ResponseEntity<ProductDTO> decrementStockByOne(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(productService.decrementProductStockByOne(id),HttpStatus.OK);
    }


    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(name = "id") Long id) {
        Boolean control=productService.deleteProduct(id);
        if(!control){
            throw new CustomApplicationException("Product not found!");
        }
        return new ResponseEntity<>(control, HttpStatus.OK);
    }
}
