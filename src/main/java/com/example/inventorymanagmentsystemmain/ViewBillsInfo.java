package com.example.inventorymanagmentsystemmain;

public class ViewBillsInfo {
    public ViewBillsInfo(String product, int quantity, float amount) {
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    private String product;
    private int quantity;
    private float amount;

}
