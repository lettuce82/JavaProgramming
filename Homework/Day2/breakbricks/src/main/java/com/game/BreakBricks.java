package com.game;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class BreakBricks {
    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = 700;
    static final int WALL_THICKNESS = 50;

    static final int MIN_RADIUS = 20;
    static final int MAX_RADIUS = 50;
    static final int BALL_COUNT = 5;
    static final int BOX_COUNT = 5;

    static final int MIN_WIDTH = 20;
    static final int MAX_WIDTH = 50;
    static final int MIN_HEIGHT = 20;
    static final int MAX_HEIGHT = 50;

    static final int MIN_DELTA = 10;
    static final int MAX_DELTA = 30;
    static final int DEFAULT_DT = 40;

    static final Color[] COLOR_TABLE = { Color.BLACK, Color.RED, Color.BLUE, Color.YELLOW };
    private static void initializeWorld(MovableWorld world) {
        // Add walls
        addWalls(world);

        // Add bricks
        addBricks(world);

        // Add movable box
        HandleBox movableBox = new HandleBox(FRAME_WIDTH / 2 - 150 / 2, 500, 150, 30, Color.PINK);
        world.add(movableBox);

        // Add bounded balls
        addBoundedBall(world);

        // Set player manager
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer(new Player());
        playerManager.addPlayer(new Player());
        world.setPlayerManager(playerManager);

        // Set world properties
        world.setDT(DEFAULT_DT);
        world.setMaxMoveCount(2000);
    }

    private static void addWalls(MovableWorld world) {
        BoundedBox topWall = new BoundedBox(0, 0, FRAME_WIDTH, WALL_THICKNESS);
        BoundedBox bottomWall = new BoundedBox(0, FRAME_HEIGHT - WALL_THICKNESS, FRAME_WIDTH, WALL_THICKNESS);
        BoundedBox leftWall = new BoundedBox(0, WALL_THICKNESS, WALL_THICKNESS, FRAME_HEIGHT - 2 * WALL_THICKNESS);
        BoundedBox rightWall = new BoundedBox(FRAME_WIDTH - WALL_THICKNESS, WALL_THICKNESS, WALL_THICKNESS,
                FRAME_HEIGHT - 2 * WALL_THICKNESS);
        world.add(topWall);
        world.add(bottomWall);
        world.add(leftWall);
        world.add(rightWall);
    }

    private static void addBricks(MovableWorld world) {
        Bricks bricks = new Bricks(FRAME_WIDTH, WALL_THICKNESS);
        List<Box> brickList = bricks.getBricks();
        for (Box box : brickList) {
            world.add(box);
        }
    }

    private static void addBoundedBall(MovableWorld world) {
        // BoundedBall 생성 후에 랜덤한 초기 방향 설정
        BoundedBall boundedBall = new BoundedBall(600, 450, 10);
        Random random = new Random();
        // 랜덤한 x축 방향 설정 (-1, 0, 1 중 하나)
        int dx = random.nextInt(3) - 1;
        // 랜덤한 y축 방향 설정 (-1, 0, 1 중 하나)
        int dy = random.nextInt(3) - 1;
        // 방향이 0인 경우를 방지하기 위해 0일 경우에는 1로 설정
        if (dx == 0)
            dx = 1;
        if (dy == 0)
            dy = 1;
        // 랜덤한 방향으로 설정
        boundedBall.setDX(dx * (random.nextDouble() * (MAX_DELTA - MIN_DELTA + 1.0) + MIN_DELTA));
        boundedBall.setDY(dy * (random.nextDouble() * (MAX_DELTA - MIN_DELTA + 1.0) + MIN_DELTA));
        world.add(boundedBall);
    }

    public static void Start() {
        JFrame frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MovableWorld world = new MovableWorld();
        BreakBricks.initializeWorld(world);
        frame.add(world);
        world.setDT(DEFAULT_DT);
        world.setMaxMoveCount(2000);
        frame.setVisible(true);
        world.run();
    }
}
