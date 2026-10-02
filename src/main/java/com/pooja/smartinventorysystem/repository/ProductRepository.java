package com.pooja.smartinventorysystem.repository;

import com.pooja.smartinventorysystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products with quantity below the given threshold
    List<Product> findByQuantityLessThan(int quantity);

    // Search products by name
    List<Product> findByProductNameContainingIgnoreCase(String productName);

    // Search products by category
    List<Product> findByCategoryIgnoreCase(String category);
}