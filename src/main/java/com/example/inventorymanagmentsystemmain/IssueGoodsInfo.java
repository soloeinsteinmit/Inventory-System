package com.example.inventorymanagmentsystemmain;


/**
 * Class for storing each issued goods
 * */
public class IssueGoodsInfo {
    private String product;
    private int quantity;
    private float unitPrice;


    /**
     *Constructor for storing issued each issued good
     * */
    public IssueGoodsInfo(String product, int quantity, float unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
