package com.example;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam2 {
    public static void main(String[] args) {
        try {
        String jsonString = "{\"address\":{\"code\":13487,\"city\":\"Seongnam\"},\"name\":\"nhn\"}";
        JSONObject object = new JSONObject(jsonString);
        
        System.out.println(object);
        // {"address":{"code":13487,"city":"Seongnam"},"name":"nhn"}

        } catch(JSONException e) {
            System.err.println(e.getMessage());
        }
    }
}
