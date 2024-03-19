package com.example;

import org.json.JSONObject;

public class Person {
    String name;
    JSONObject addressObject = new JSONObject();

    public Person(String name) {
        this.name = name;
        addressObject.put("code", 13487);
        addressObject.put("city", "Seongnam");
    }

    public JSONObject getAddress() {
        return this.addressObject;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
