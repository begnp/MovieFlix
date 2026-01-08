package com.movieflix.movieflix.service;

import com.movieflix.movieflix.entity.Category;
import com.movieflix.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public Category findCategoryById(Long id) {
        Optional<Category> optCategory = repository.findById(id);
        if (optCategory.isPresent()) {
            return optCategory.get();
        }
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
