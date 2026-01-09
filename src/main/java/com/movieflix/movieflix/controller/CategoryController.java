package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.entity.Category;
import com.movieflix.movieflix.mapper.CategoryMapper;
import com.movieflix.movieflix.request.CategoryRequest;
import com.movieflix.movieflix.response.CategoryResponse;
import com.movieflix.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList());
        /*List<Category> categories = categoryService.findAll();
        List<CategoryResponse> list = categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok(list);*/
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.save(newCategory);
        CategoryResponse category = CategoryMapper.toCategoryResponse(savedCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        Optional<Category> optCategory = categoryService.findById(id);
        if (optCategory.isPresent()) {
            CategoryResponse category = CategoryMapper.toCategoryResponse(optCategory.get());
            return ResponseEntity.ok().body(category);
        }
        return ResponseEntity.notFound().build();
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) { // Esse Void precisa ter "V" em Uppercase
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
