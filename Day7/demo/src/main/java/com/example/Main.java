package com.example;

import java.io.Console;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    
    public static void main(String[] args) {

        Console console = System.console();
        JSONArray userArray = new JSONArray();
        JSONArray itemArray = new JSONArray();

        JSONObject userObject = new JSONObject();
        JSONObject iemObject = new JSONObject();

        while (true) {
            System.out.print("command : ");
            String line = console.readLine();
            
            String[] lineToken = line.split(" ");

            //User
            if (lineToken[0].equals("user")) {
                if (lineToken[1].equals("add")) {
                    User user = new User(lineToken[2], lineToken[3]);
                    JSONObject temp = new JSONObject(user);
                    userArray.put(temp);
                    userObject.put("user", userArray);
                    System.out.println("사용자 " + lineToken[2] + "가 추가되었습니다.");
                }
                if (lineToken[1].equals("del")) {
                    for (int i = 0; i < userArray.length(); i++) {
                        if (((JSONObject)(userArray.get(i))).getString("id").equals(lineToken[2])) {
                            userArray.remove(i);
                            System.out.println("사용자 " + lineToken[2] + "가 삭제되었습니다.");
                        }
                    }
                }
                if (lineToken[1].equals("list")) {
                    System.out.println(userObject);
                }
            }

            //Item
            if (lineToken[0].equals("item")) {
                if (lineToken[1].equals("add")) {
                    Item item = new Item(lineToken[2], lineToken[3], Integer.parseInt(lineToken[4]), Integer.parseInt(lineToken[5]), Integer.parseInt(lineToken[6]), Integer.parseInt(lineToken[7]), Integer.parseInt(lineToken[8]));
                    JSONObject temp = new JSONObject(item);
                    itemArray.put(temp);
                    iemObject.put("user", itemArray);
                    System.out.println("사용자 " + lineToken[2] + "가 추가되었습니다.");
                }
                if (lineToken[1].equals("del")) {
                    for (int i = 0; i < itemArray.length(); i++) {
                        if (((JSONObject)(itemArray.get(i))).getString("id").equals(lineToken[2])) {
                            itemArray.remove(i);
                            System.out.println("사용자 " + lineToken[2] + "가 삭제되었습니다.");
                        }
                    }
                }
                if (lineToken[1].equals("list")) {
                    System.out.println(iemObject);
                }
            }
    
            if (line.equals("quit")) {
                break;
            }
        }
    }
}