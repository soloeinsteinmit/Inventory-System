package com.example.inventorymanagmentsystemmain;

public class ViewGoodsInfo {
    private int number;
    private String goodsName;
    private String categoryName;
    private int quantity;
    private String sellingPrice;
    private String profit;

    public ViewGoodsInfo(int number, String goodsName, String categoryName,
                 int quantity, String sellingPrice, String profit){
        this.number = number;
        this.goodsName = goodsName;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.profit = profit;

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public String getProfit() {
        return profit;
    }


}
