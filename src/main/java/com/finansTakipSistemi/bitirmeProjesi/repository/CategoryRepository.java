package com.finansTakipSistemi.bitirmeProjesi.repository;

import com.finansTakipSistemi.bitirmeProjesi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}