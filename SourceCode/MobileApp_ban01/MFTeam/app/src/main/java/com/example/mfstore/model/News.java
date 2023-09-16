package com.example.mfstore.model;

import androidx.annotation.NonNull;

public class News {
    private String bigTitle;
    private String smallTitle;
    private String link;
    private int img;


    public News() {
    }

    public News(String bigTitle, String smallTitle, String link, int img) {
        this.bigTitle = bigTitle;
        this.smallTitle = smallTitle;
        this.link = link;
        this.img = img;
    }

    public String getBigTitle() {
        return bigTitle;
    }

    public void setBigTitle(String bigTitle) {
        this.bigTitle = bigTitle;
    }

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int  img) {
        this.img = img;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }


}
