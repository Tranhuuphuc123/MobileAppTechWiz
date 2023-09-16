package com.example.mfstore.model;

public class ProductTypeModels {
    private String id;
    private String img;
    private String name;

    public ProductTypeModels() {
    }

    public ProductTypeModels(String id, String img, String name) {
        this.id =id;
        this.img = img;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
