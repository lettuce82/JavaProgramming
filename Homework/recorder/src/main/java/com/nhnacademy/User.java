package com.nhnacademy;

import org.json.JSONObject;

public class User {
    String id;
    String nickName;
    static JSONObject userObject = new JSONObject();
    //생성된 데이터는 JSON 문자열로 파일에 저장한다.

    public User(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public void list() {
        System.out.println(userObject);
    }

    public String getId() {
        return this.id;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String nickName) {
        this.nickName = nickName;
    }
}
