package com.pooja.smartinventorysystem.repository;

import com.pooja.smartinventorysystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}