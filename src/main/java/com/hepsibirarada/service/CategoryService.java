package com.hepsibirarada.service;

import com.hepsibirarada.convert.Converter;
import com.hepsibirarada.dtos.CategoryDTO;
import com.hepsibirarada.model.Category;
import com.hepsibirarada.model.Product;
import com.hepsibirarada.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final Converter converter;

    //SAVE
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        categoryRepository.save(category);
        return categoryDTO;
    }

    public List<CategoryDTO> saveAllCategory(List<CategoryDTO> categoryDTOS) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO categoryDTO : categoryDTOS) {
            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            categories.add(category);
        }
        categoryRepository.saveAll(categories);
        return categoryDTOS;
    }

    //FIND

    public CategoryDTO findCategoryByCategoryName(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category != null) {
            return converter.categoryToCategoryDTO(category);
        }
        return null;
    }


    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category a : categories) {
            categoryDTOS.add(converter.categoryToCategoryDTO(a));
        }
        return categoryDTOS;
    }

    public CategoryDTO findCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        CategoryDTO categoryDTO = new CategoryDTO();
        return optionalCategory.map(category -> converter.categoryToCategoryDTO(category)).orElse(null);
    }

    //DELETE

    public Boolean deleteCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.delete(optionalCategory.get());
            for (Product product : optionalCategory.get().getProducts()) {
                product.getCategories().remove(optionalCategory.get());
            }
            return true;
        }
        return false;
    }


    public Boolean updateCategory(Long id, String categoryName) {
        Category category = new Category();
        category.setId(id);
        category.setCategoryName(categoryName);
        categoryRepository.save(category);
        return true;
    }
}
