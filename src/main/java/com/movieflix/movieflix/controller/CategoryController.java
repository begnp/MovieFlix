package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.entity.Category;
import com.movieflix.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping("/a")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

}
