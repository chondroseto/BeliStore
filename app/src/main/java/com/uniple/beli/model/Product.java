package com.uniple.beli.model;

public class Product {
    private int id;
    private String productImg;
    private String productName;
    private int productPrice;

    public Product(int id, String productImg, String productName, int productPrice) {
        this.id = id;
        this.productImg = productImg;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getId() {
        return id;
    }

    public String getProductImg() {
        return productImg;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public void setPrductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}

