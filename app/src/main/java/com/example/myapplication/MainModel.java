package com.example.myapplication;

public class MainModel {


    String name,address,email,id,phone;

    public MainModel(String name, String address, String email, String id, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.id = id;
        this.phone = phone;
    }

    public MainModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
