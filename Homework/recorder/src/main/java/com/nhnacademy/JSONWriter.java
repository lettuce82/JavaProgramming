package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {

    public void write(JSONObject newData, String dbFileName, String type) {
        File file = new File(dbFileName);
        JSONObject existingData = new JSONObject();
    
        // 파일이 이미 존재하는 경우 기존 데이터를 읽어옴
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                // 파일에서 JSON 데이터 읽기
                existingData = new JSONObject(new JSONTokener(reader));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        // 새로운 데이터 추가
        JSONArray dataArray = newData.getJSONArray(type);
        JSONArray existingArray = existingData.optJSONArray(type);
    
        if (existingArray == null) {
            // 기존에 해당 유형의 데이터가 없는 경우 새로 추가
            existingData.put(type, dataArray);
        } else {
            // 기존에 해당 유형의 데이터가 있는 경우 기존 데이터와 합침
            for (int i = 0; i < dataArray.length(); i++) {
                existingArray.put(dataArray.get(i));
            }
            existingData.put(type, existingArray);
        }
    
        // JSON 파일에 데이터 쓰기
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(existingData.toString(3));
            fileWriter.flush();
            System.out.println("데이터를 파일에 성공적으로 썼습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(String dbFileName, String type) {
        File file = new File(dbFileName);
        // 파일이 존재하는지 확인
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다.");
            return;
        }
    
        try (FileReader reader = new FileReader(file)) {
            // 파일에서 JSON 데이터 읽기
            JSONObject jsonData = new JSONObject(new JSONTokener(reader));
    
            // JSON 데이터 파싱
            JSONArray objectArray = jsonData.getJSONArray(type);
    
            for (int i = 0; i < objectArray.length(); i++) {
                JSONObject item = objectArray.getJSONObject(i);
                if (type.equals("user")) {
                    String id = item.getString("id");
                    String nickName = item.getString("nickName");
                    System.out.println("사용자 ID: " + id + ", 이름: " + nickName);
                } else if (type.equals("item")) {
                    String id = item.getString("id");
                    String model = item.getString("model");
                    int stamina = item.getInt("stamina");
                    int power = item.getInt("power");
                    int defence = item.getInt("defence");
                    int moveSpeed = item.getInt("moveSpeed");
                    int attackSpeed = item.getInt("attackSpeed");
                    System.out.println("무기 ID: " + id + ", 모델명: " + model + ", 체력: " + stamina + ", 공격력: " + power +
                            ", 방어력: " + defence + ", 이동속도: " + moveSpeed + ", 공격속도: " + attackSpeed);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
