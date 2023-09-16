package com.example.mfstore.model;

public class ProductModels {
  private String productName;
  private String productPrice;
  private String productImg;

    public ProductModels(String productName, String productPrice, String productImg) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

    public ProductModels() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
