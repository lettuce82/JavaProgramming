package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bricks {
    List<Box> bricks = new ArrayList<>();
    int row = 5;
    int column = 4;
    int boxHeight = 50;

    public Bricks(int FRAME_WIDTH, int WALL_THICKNESS) {
        int boxRowWidth = (FRAME_WIDTH - WALL_THICKNESS * 2) / row;
        Random random = new Random();
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (random.nextBoolean()) {
                    bricks.add(new BreakableBox(WALL_THICKNESS + boxRowWidth * j, WALL_THICKNESS + boxHeight * i, boxRowWidth, boxHeight, random.nextInt(5) + 1));
                } else {
                    bricks.add(new BoundedBox(WALL_THICKNESS + boxRowWidth * j, WALL_THICKNESS + boxHeight * i, boxRowWidth, boxHeight));
                }
            }
        }
    }

    public List<Box> getBricks() {
        return bricks;
    }
}
