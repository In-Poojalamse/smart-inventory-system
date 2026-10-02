package com.pooja.smartinventorysystem.controller;

import com.pooja.smartinventorysystem.entity.StockMovement;
import com.pooja.smartinventorysystem.service.StockMovementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    // Get all stock movements
    @GetMapping
    public List<StockMovement> getMovements() {
        return stockMovementService.getAllMovements();
    }

    // Add stock movement
    @PostMapping
    public StockMovement addMovement(@RequestBody StockMovement movement) {
        return stockMovementService.saveMovement(movement);
    }
}