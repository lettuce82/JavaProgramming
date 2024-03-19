package com.nhnacademy;

import org.json.JSONArray;
import org.json.JSONObject;

public class Item {
    String id;
    String model;
    int stamina;
    int power;
    int defence;
    int moveSpeed;
    int attackSpeed;
    static JSONObject itemObject = new JSONObject();
    static JSONArray itemArray = new JSONArray();

    public Item(String id, String model, int stamina, int power, int defence, int moveSpeed, int attackSpeed) {
        this.id = id;
        this.model = model;
        this.stamina = stamina;
        this.power = power;
        this.defence = defence;
        this.moveSpeed = moveSpeed;
        this.attackSpeed = attackSpeed;
    }

    public String getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }

    public int getStamina() {
        return this.stamina;
    }

    public int getPower() {
        return this.power;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getMoveSpeed() {
        return this.moveSpeed;
    }

    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setDefense(int defence) {
        this.defence = defence;
    }

    public void setmMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
}
