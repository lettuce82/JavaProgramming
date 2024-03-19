package com.example;

public class Record {
    int matchNum;
    int winsNum;

    public Record(int matchNum, int winsNum) {
        this.matchNum = matchNum;
        this.winsNum = winsNum;
    }

    public int getMatchNum() {
        return this.matchNum;
    }

    public int getWinsNum() {
        return this.winsNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public void setWinsNum(int winsNum) {
        this.winsNum = winsNum;
    }
}
