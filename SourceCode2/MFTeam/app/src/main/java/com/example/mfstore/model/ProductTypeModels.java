package com.example.mfstore.model;

public class ProductTypeModels {
    private String id;
    private String img;
    private String name;
    private String type;

    public ProductTypeModels() {
    }

    public ProductTypeModels(String id, String img, String name, String type) {
        this.id =id;
        this.img = img;
        this.name = name;
        this.type=type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
