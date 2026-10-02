package com.pooja.smartinventorysystem.repository;

import com.pooja.smartinventorysystem.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}