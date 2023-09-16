package com.example.mfstore.model;

import java.io.Serializable;

public class Photo implements Serializable {
    private int ResourceID;

    public Photo(int resourceID) {
        ResourceID = resourceID;
    }

    public int getResourceID() {
        return ResourceID;
    }

    public void setResourceID(int resourceID) {
        ResourceID = resourceID;
    }
}
