package com.pooja.smartinventorysystem.service;

import com.pooja.smartinventorysystem.dto.DashboardSummary;
import com.pooja.smartinventorysystem.entity.Product;
import com.pooja.smartinventorysystem.exception.ProductNotFoundException;
import com.pooja.smartinventorysystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Add product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Update product
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    // Delete product
    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
    }

    // Get low-stock products
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findByQuantityLessThan(threshold);
    }

    // Search products by name
    public List<Product> searchProducts(String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name);
    }

    // Search products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // Get dashboard summary
    public DashboardSummary getDashboardSummary() {

        List<Product> products = productRepository.findAll();

        long totalProducts = products.size();

        long totalQuantity = products.stream()
                .mapToLong(Product::getQuantity)
                .sum();

        long lowStockProducts = products.stream()
                .filter(product -> product.getQuantity() < 10)
                .count();

        double totalInventoryValue = products.stream()
                .mapToDouble(product ->
                        product.getQuantity() * product.getPrice())
                .sum();

        return new DashboardSummary(
                totalProducts,
                totalQuantity,
                lowStockProducts,
                totalInventoryValue
        );
    }
}