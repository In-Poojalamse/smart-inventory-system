package com.pooja.smartinventorysystem.dto;

public class DashboardSummary {

    private long totalProducts;
    private long totalQuantity;
    private long lowStockProducts;
    private double totalInventoryValue;

    public DashboardSummary() {
    }

    public DashboardSummary(long totalProducts,
                            long totalQuantity,
                            long lowStockProducts,
                            double totalInventoryValue) {
        this.totalProducts = totalProducts;
        this.totalQuantity = totalQuantity;
        this.lowStockProducts = lowStockProducts;
        this.totalInventoryValue = totalInventoryValue;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public long getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(long lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }

    public double getTotalInventoryValue() {
        return totalInventoryValue;
    }

    public void setTotalInventoryValue(double totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }
}