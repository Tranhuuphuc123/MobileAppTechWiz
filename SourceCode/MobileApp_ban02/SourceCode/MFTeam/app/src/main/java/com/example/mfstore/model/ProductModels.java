package com.example.mfstore.model;

import java.io.Serializable;

public class ProductModels implements Serializable {
  private String productName;
  private String productPrice;
  private String productImg;
  private String type;

    public ProductModels(String productName, String productPrice, String productImg, String type) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
