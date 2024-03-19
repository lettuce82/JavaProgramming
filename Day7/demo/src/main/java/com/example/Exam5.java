package com.example;

import org.json.JSONObject;

public class Exam5 {
    public static void main(String[] args) {
        JSONObject customer = new JSONObject();
        customer.put("name", "nhn");
        customer.put("age", 10);

        Object nameObject = customer.get("name");
        System.out.println("Name type : " + nameObject.getClass().getTypeName());
        if (nameObject instanceof String) {
            System.out.println("Name is String");
        }
        
        Object ageObject = customer.get("age");
        System.out.println("Age type : " + ageObject.getClass().getTypeName());
        //System.out.println("Age type : " + customer.getString("age")); -> Integer라서 getString으로 하면 오류가 남
    }
}
