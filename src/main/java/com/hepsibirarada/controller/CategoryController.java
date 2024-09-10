package com.hepsibirarada.controller;

import com.hepsibirarada.dtos.CategoryDTO;
import com.hepsibirarada.model.Category;
import com.hepsibirarada.service.CategoryService;
import com.hepsibirarada.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //Post
    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.saveCategory(categoryDTO), HttpStatus.CREATED);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<CategoryDTO>> saveAllCategories(@RequestBody List<CategoryDTO> categoryDTOS) {
        return new ResponseEntity<>(categoryService.saveAllCategory(categoryDTOS), HttpStatus.OK);
    }
    //Get
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CategoryDTO> findByUsername(@PathVariable String categoryName) {
        return new ResponseEntity<>(categoryService.findByCategoryName(categoryName), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCategory(@PathVariable(name = "id") Long id,@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryDTO.getCategoryName()), HttpStatus.OK);
    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.deleteById(id), HttpStatus.OK);
    }

}
