package com.example.myapplication;

public class DataClass {




    private  String name ;
    private   String address;
    private   String email;
    private  String idproof;


    public DataClass(String name, String address, String email, String idproof) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.idproof = idproof;


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

    public String getIdproof() {
        return idproof;
    }

    public void setIdproof(String idproof) {
        this.idproof = idproof;
    }
}
