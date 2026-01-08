package com.movieflix.movieflix.repository;

import com.movieflix.movieflix.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Annotation não obrigatoria, por não atrapalhar o funcionamento
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
