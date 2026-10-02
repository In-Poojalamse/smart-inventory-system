package com.pooja.smartinventorysystem.service;

import com.pooja.smartinventorysystem.entity.Product;
import com.pooja.smartinventorysystem.entity.StockMovement;
import com.pooja.smartinventorysystem.repository.ProductRepository;
import com.pooja.smartinventorysystem.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository,
                                ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    public List<StockMovement> getAllMovements() {
        return stockMovementRepository.findAll();
    }

    @Transactional
    public StockMovement saveMovement(StockMovement movement) {

        // Validate quantity
        if (movement.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        // Validate stock type
        if (!movement.getType().equalsIgnoreCase("IN")
                && !movement.getType().equalsIgnoreCase("OUT")) {
            throw new RuntimeException("Stock type must be IN or OUT");
        }

        // Find product
        Product product = productRepository.findById(movement.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Stock IN
        if (movement.getType().equalsIgnoreCase("IN")) {
            product.setQuantity(
                    product.getQuantity() + movement.getQuantity()
            );
        }

        // Stock OUT
        else {
            if (product.getQuantity() < movement.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            product.setQuantity(
                    product.getQuantity() - movement.getQuantity()
            );
        }

        // Save updated product
        System.out.println("DEBUG PRODUCT QUANTITY = " + product.getQuantity());
        productRepository.saveAndFlush(product);

        // Save stock movement history
        return stockMovementRepository.save(movement);
    }
}