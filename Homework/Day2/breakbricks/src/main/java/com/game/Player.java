package com.game;

public class Player {
    private int score;
    private static int nextId = 1;
    private int id;

    public Player() {
        this.score = 0;
        this.id = nextId++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void increaseScore(int points) {
        score += points;
    }
}
