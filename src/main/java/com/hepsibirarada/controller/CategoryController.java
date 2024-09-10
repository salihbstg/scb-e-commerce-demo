package com.hepsibirarada.controller;

import com.hepsibirarada.dtos.CategoryDTO;
import com.hepsibirarada.exception.CustomApplicationException;
import com.hepsibirarada.service.CategoryService;
import lombok.RequiredArgsConstructor;
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
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }
    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CategoryDTO> findByUsername(@PathVariable String categoryName) {
        CategoryDTO categoryDTO=categoryService.findCategoryByCategoryName(categoryName);
        if(categoryDTO==null){
            throw new CustomApplicationException("Category Not Found!");
        }
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable(name = "id") Long id) {
        CategoryDTO categoryDTO=categoryService.findCategoryById(id);
        if(categoryDTO==null){
            throw new CustomApplicationException("Category Not Found!");
        }
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
    //Update
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateCategory(@PathVariable(name = "id") Long id,@RequestBody CategoryDTO categoryDTO) {
        Boolean control=categoryService.updateCategory(id, categoryDTO.getCategoryName());
        if(!control){
            throw new CustomApplicationException("Category Not Found!");
        }
        return new ResponseEntity<>(control, HttpStatus.OK);
    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Boolean control=categoryService.deleteCategoryById(id);
        if(!control){
            throw new CustomApplicationException("Category Not Found!");
        }
        return new ResponseEntity<>(control, HttpStatus.OK);
    }

}
