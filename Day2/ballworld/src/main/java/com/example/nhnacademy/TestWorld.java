package com.example.nhnacademy;


import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestWorld {
    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;
    static final int MIN_RADIUS = 20;
    static final int MAX_RADIUS = 50;
    static final int MIN_WIDTH = 20;
    static final int MAX_WIDTH = 50;
    static final int MIN_HEIGHT = 20;
    static final int MAX_HEIGHT = 50;
    static final int BALL_COUNT = 5;
    static final int BOX_COUNT = 5;
    static final int MIN_DELTA = 10;
    static final int MAX_DELTA = 30;
    static final int DEFAULT_DT = 30;
    static final int WALL_THICKNESS = 100;

    static final Color[] COLOR_TABLE = {
            Color.BLACK,
            Color.RED,
            Color.BLUE,
            Color.YELLOW
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MovableWorld world = new MovableWorld();

        //world.setBounds(-WALL_THICKNESS, -WALL_THICKNESS, FRAME_WIDTH + WALL_THICKNESS * 2, FRAME_HEIGHT + WALL_THICKNESS * 2);

        // 테두리 벽 추가
        BoundedBox topWall = new BoundedBox(100, -WALL_THICKNESS + 100, FRAME_WIDTH, WALL_THICKNESS);
        BoundedBox bottomWall = new BoundedBox(100, FRAME_HEIGHT + 100, FRAME_WIDTH, WALL_THICKNESS);
        BoundedBox leftWall = new BoundedBox(-WALL_THICKNESS + 100, 0, WALL_THICKNESS, FRAME_HEIGHT + 200);
        BoundedBox rightWall = new BoundedBox(FRAME_WIDTH + 100, 0, WALL_THICKNESS, FRAME_HEIGHT + 200);

        world.add(topWall);
        world.add(bottomWall);
        world.add(leftWall);
        world.add(rightWall);

        frame.add(world);
        

        Random random = new Random();

        //랜덤 볼 여러개
        while (world.getCount() < BALL_COUNT + BOX_COUNT) {
            try {
                Bounded object;
                if (random.nextBoolean()) {
                    object = new BoundedBall(random.nextInt(FRAME_WIDTH), random.nextInt(FRAME_HEIGHT),
                            MIN_RADIUS + random.nextInt(MAX_RADIUS - MIN_RADIUS + 1),
                            COLOR_TABLE[random.nextInt(COLOR_TABLE.length)]);
                } else {
                    object = new BoundedBox(random.nextInt(FRAME_WIDTH), random.nextInt(FRAME_HEIGHT),
                            MIN_WIDTH + random.nextInt(MAX_WIDTH - MIN_WIDTH + 1),
                            MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT + 1));
                }

                int dx = MIN_DELTA - random.nextInt(MAX_DELTA - MIN_DELTA + 1);
                int dy = MIN_DELTA - random.nextInt(MAX_DELTA - MIN_DELTA + 1);
                
                ((Movable) object).setDX(dx);
                ((Movable) object).setDY(dy);

                world.add((Regionable) object);

            } catch (IllegalArgumentException ignore) {
                // 예외 처리 생략
            }
        }
        
        world.setDT(DEFAULT_DT);
        frame.setVisible(true);

        world.setMaxMoveCount(20000);
        world.run();
    }
}
