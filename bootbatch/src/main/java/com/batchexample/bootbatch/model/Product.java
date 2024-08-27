package com.batchexample.bootbatch.model;


import java.math.BigDecimal;

public class Product {
    private int productId;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal discountedPrice;

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
    public Product() {
    }
    public Product(int productId, String title, String description, BigDecimal price, BigDecimal discount, BigDecimal discountedPrice) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.discountedPrice = discountedPrice;
    }
}

