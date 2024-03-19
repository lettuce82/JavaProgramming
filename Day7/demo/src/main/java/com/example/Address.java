package com.example;

public class Address {
    int code;
    String city;

    public Address(int code, String city) {
        this.code = code;
        this.city = city;
    }

    public int getCode() {
        return this.code;
    }

    public String getCity() {
        return this.city;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
