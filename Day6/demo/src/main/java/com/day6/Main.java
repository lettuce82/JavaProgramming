package com.day6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String getType(String args) {
        Pattern intPattern = Pattern.compile("^\\s*\\d+\\s*$"); //숫자가 하나 이상이고(\\d+) 시작점(^)과 끝점($)에 스페이스 허용(\\s*)
        Matcher intMatcher = intPattern.matcher(args);

        if (intMatcher.find()) {
            return "int";
        } else {
            return "String";
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            try {
                int value = Integer.parseInt(arg);
                System.out.println("int: " + value);
            } catch (NumberFormatException ignore) {
                System.out.println("String : " + arg);
            }
        }
        System.out.println("-------------------");

        //내 풀이
        for (String string : args) {
            System.out.println(getType(string) + " : " + string);
        }
    }
}
