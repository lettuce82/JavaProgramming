package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exam6 {
    public static void main(String[] args) {
        JSONArray jsonArray2 = new JSONArray(new String[]{"갈매기", "참새", "펭귄"});
        JSONArray jsonArray3 = new JSONArray(new String[]{"사자", "호랑이", "말"});
        JSONObject birdObject = new JSONObject();
        birdObject.put("조류", jsonArray2);
        JSONObject mammalObject = new JSONObject();
        mammalObject.put("포유류", jsonArray3);

        JSONArray jsonArray4 = new JSONArray(new JSONObject[]{birdObject, mammalObject});
        JSONObject object = new JSONObject();
        object.put("동물", jsonArray4);

        System.out.println(object);
        //{"동물":[{"조류":["갈매기","참새","펭귄"]},{"포유류":["사자","호랑이","말"]}]}
    }
}
