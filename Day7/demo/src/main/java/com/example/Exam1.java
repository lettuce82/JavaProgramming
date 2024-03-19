package com.example;

import org.json.JSONObject;

public class Exam1 {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        JSONObject addressObject = new JSONObject();
        addressObject.put("code", 13487);
        addressObject.put("city", "Seongnam");

        object.put("address", addressObject);
        object.put("name", "nhn"); //생성하기
        object.put("name", "nhn academy"); //생성 후 put()은 값 변경

        System.out.println(object);
        //{"address":{"code":13487,"city":"Seongnam"},"name":"nhn"}

        String jsonString = "{\"address\":{\"code\":13487,\"city\":\"Seongnam\"},\"name\":\"nhn\"}";
        JSONObject singleObject = new JSONObject(jsonString);
        System.out.println(singleObject);
    }
}
