package com.example.inventorymanagmentsystemmain;

public class ViewIssuedGoodsInfo{
    private int number;
    private String goodsName;
    private String categoryName;
    private int quantitySold;
    private String receiptId;
    private String datetimeSold;

    public ViewIssuedGoodsInfo(int number, String goodsName, String categoryName, int quantitySold, String receiptId, String datetimeSold) {
        this.number = number;
        this.goodsName = goodsName;
        this.categoryName = categoryName;
        this.quantitySold = quantitySold;
        this.receiptId = receiptId;
        this.datetimeSold = datetimeSold;
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

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getDatetimeSold() {
        return datetimeSold;
    }

    public void setDatetimeSold(String datetimeSold) {
        this.datetimeSold = datetimeSold;
    }
}
