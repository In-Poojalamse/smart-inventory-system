package com.pooja.smartinventorysystem.controller;

import com.pooja.smartinventorysystem.dto.DashboardSummary;
import com.pooja.smartinventorysystem.entity.Product;
import com.pooja.smartinventorysystem.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    // Add product
    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // Update product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

    // Get low-stock products
    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts(
            @RequestParam(defaultValue = "10") int threshold) {
        return productService.getLowStockProducts(threshold);
    }

    // Search products by name
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        return productService.searchProducts(name);
    }

    // Search products by category
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(
            @PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // Dashboard summary
    @GetMapping("/dashboard")
    public DashboardSummary getDashboardSummary() {
        return productService.getDashboardSummary();
    }
}