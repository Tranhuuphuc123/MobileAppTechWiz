package com.example.mfstore.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Customers {
    private String customerID;
    private String customerName;
    private String address;
    private String email;
    private String phone;
    private LocalDate dateCreated;
    private String password;
    private String image;
    private String status;

    public Customers(){};

    public Customers(String customerID, String customerName, String address, String email, String phone, LocalDate dateCreated, String password, String image, String status) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateCreated = dateCreated;
        this.password = password;
        this.image = image;
        this.status = status;
    }

    public Customers(String fullname, String address, String email, String phone, LocalDate toString, String img, String status) {
        this.customerName = fullname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateCreated = toString;
        this.image = img;
        this.status = status;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> toMap(){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("CustomerName", customerName);
        hashMap.put("Phone", phone);
        hashMap.put("Email",email);
        hashMap.put("Address",address);
        hashMap.put("DateCreated",dateCreated.toString());
        hashMap.put("Image",image);
        hashMap.put("Status",status);

        return hashMap;
    }
}