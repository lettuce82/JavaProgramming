package com.game;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TestWorld {
    static final int FRAME_WIDTH = 800;
    static final int FRAME_HEIGHT = 600;
    static final int WALL_THICKNESS = 100;
    
    
    static final int MIN_WIDTH = 20;
    static final int MAX_WIDTH = 50;
    static final int MIN_HEIGHT = 20;
    static final int MAX_HEIGHT = 50;

    static final int MIN_DELTA = 10;
    static final int MAX_DELTA = 30;
    static final int DEFAULT_DT = 30;
    
    

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

        //BoundedBall 
        BoundedBall boundedBall = new BoundedBall(150, 150, 50);
        boundedBall.setDX(MIN_DELTA);
        boundedBall.setDY(MIN_DELTA);
        world.add(boundedBall);

        //BoundedBox
        BoundedBox topWall = new BoundedBox(0, 0, FRAME_WIDTH, WALL_THICKNESS);
        BoundedBox bottomWall = new BoundedBox(0, FRAME_HEIGHT - WALL_THICKNESS, FRAME_WIDTH, WALL_THICKNESS);
        BoundedBox leftWall = new BoundedBox(0, WALL_THICKNESS, WALL_THICKNESS, FRAME_HEIGHT - 2 * WALL_THICKNESS);
        BoundedBox rightWall = new BoundedBox(FRAME_WIDTH - WALL_THICKNESS, WALL_THICKNESS, WALL_THICKNESS, FRAME_HEIGHT - 2 * WALL_THICKNESS);
        world.add(topWall);
        world.add(bottomWall);
        world.add(leftWall);
        world.add(rightWall);

        //MovableBox
        HandleBox movableBox = new HandleBox(200, 200, 50, 150);
        world.add(movableBox);

        //BreakableBox
        BreakableBox bric = new BreakableBox(400, 350, 150, 50, 5);
        world.add(bric);

        //movableBall Test 완료
        // MovableBall movableBall = new MovableBall(300, 300, 50);
        // movableBall.setDX(MIN_DELTA);
        // movableBall.setDY(MIN_DELTA);
        // world.add(movableBall);

        //paintableBall Test 완료
        //world.add(new PaintableBall(300, 300, 50));

        
        world.setDT(DEFAULT_DT);

        world.setMaxMoveCount(2000);
        frame.add(world);
        frame.setVisible(true);
        world.run();
        
    }
}
