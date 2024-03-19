package com.example;

import org.json.JSONTokener;

public class Exam9 {
    public static void main(String[] args) {
        JSONTokener tokener = new JSONTokener(System.in);
        //JSONTokener tokener = new JSONTokener("{\"name\":\"nhn\"}");
        while (!tokener.end()) {
            Object object = tokener.nextValue();
            System.out.println(object.getClass().getTypeName() + " : " + object);
        }
    }
}
