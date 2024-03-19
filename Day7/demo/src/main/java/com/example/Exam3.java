package com.example;

import org.json.JSONObject;

public class Exam3 {
    public static void main(String[] args) {
        Person person = new Person("nhn");
        JSONObject object = new JSONObject(person); //fojo .. getter, setter가 있어야함
        System.out.println(object);
        //getter 메서드의 이름은 getName2라고 하면 {"name2":"nhn"} 라고 나옴 -> setter도 마찬가지
        //{"address":{"code":13487,"city":"Seongnam"},"name":"nhn"}

        ////// 다시 해보기 /////
    }
}