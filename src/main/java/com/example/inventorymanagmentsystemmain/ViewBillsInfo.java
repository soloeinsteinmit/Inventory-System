package com.example.inventorymanagmentsystemmain;
/**
 * Class for storing bills information
 * */
public class ViewBillsInfo {
    public ViewBillsInfo(String product, int quantity, float amount) {
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
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


    private String product;
    private int quantity;
    private float amount;

}
