package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

public class Db {
    private JSONArray userArray;
    private JSONArray itemArray;
    private JSONObject userObject;
    private JSONObject itemObject;
    JSONWriter jsonWriter = new JSONWriter();
    
    public Db() {
        userArray = new JSONArray();
        itemArray = new JSONArray();
        userObject = new JSONObject();
        itemObject = new JSONObject();
    }
    
    public void add(User user, String dbFileName, String type) {
        System.out.println("add 안");

        JSONObject temp = new JSONObject(user);
        userArray.put(temp);
        userObject.put(type, userArray);
        jsonWriter.write(userObject, dbFileName, type);
    }

    public void add(Item item, String dbFileName, String type) {
        System.out.println("add 안");
        JSONObject temp = new JSONObject(item);
        System.out.println("item" + item.getModel());
        itemArray.put(temp);
        itemObject.put(type, itemArray); // 이 부분도 수정이 필요할 수 있음
        jsonWriter.write(itemObject, dbFileName, type);
    }

    public JSONObject takeJsonObject() {
        return userObject;
    }

    public void list(String type, String dbFileName) {
        System.out.println("list 안");
        if (type.equals("user")) {
            System.out.println("user 안");
            jsonWriter.read(dbFileName, type);
        }
        if (type.equals("item")) {
            System.out.println("item 안");
            jsonWriter.read(dbFileName, type);
        }
    }
}
