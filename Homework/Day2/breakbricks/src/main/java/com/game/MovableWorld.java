package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class MovableWorld extends World {
    static final int DEFAULT_DT = 10;
    int moveCount;
    int maxMoveCount = 0;
    int dt = DEFAULT_DT;
    PlayerManager playerManager = new PlayerManager();
    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = 700;
    static final int WALL_THICKNESS = 100;
    BoundedBox bottomWall = new BoundedBox(0, FRAME_HEIGHT - WALL_THICKNESS, FRAME_WIDTH, WALL_THICKNESS);

    public void setDT(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }
        this.dt = dt;
    }

    public int getDT() {
        return dt;
    }

    public void reset() {
        moveCount = 0;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void move() {
        if ((getMaxMoveCount() == 0) || (getMoveCount() < getMaxMoveCount())) {
            moveObjects();
            handleCollisions();
            handleBreakables();
            moveCount++;
            repaint();
        }
    }
    
    private void moveObjects() {
        for (int i = 0; i < getCount(); i++) {
            Regionable object = get(i);
            if (object instanceof Movable) {
                ((Movable) object).move();
    
                if (object instanceof Bounded) {
                    Bounded boundedObject = (Bounded) object;
                    for (int j = 0; j < getCount(); j++) {
                        Regionable other = get(j);
    
                        if ((object != other) && (object.getRegion().intersects(other.getRegion()))) {
                            if (other instanceof Bounded) {
                                boundedObject.bounce(other);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void handleCollisions() {
        for (int i = 0; i < getCount(); i++) {
            Regionable object = get(i);
            if (object instanceof Breakable) {
                Breakable breakableObject = (Breakable) object;
                if (breakableObject instanceof BreakableBox) {
                    for (int j = 0; j < getCount(); j++) {
                        Regionable other = get(j);
                        if ((object != other) && (object.getRegion().intersects(other.getRegion()))) {
                            if (other instanceof MovableBall && breakableObject.isBroken((MovableBall) other)) {
                                PlayerManager playerManager = getPlayerManager();
                                List<Player> players = playerManager.getPlayers();
                                for (Player player : players) {
                                    if (player.getId() == 1) {
                                        ((BreakableBox) breakableObject).updatePlayerScore(player);
                                    }
                                }
                                remove(object);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void handleBreakables() {
        for (int i = 0; i < getCount(); i++) {
            Regionable object = get(i);
            if (object instanceof Breakable) {
                Breakable breakableObject = (Breakable) object;
                if (breakableObject instanceof BreakableBox) {
                    for (int j = 0; j < getCount(); j++) {
                        Regionable other = get(j);
                        if ((object != other) && (object.getRegion().intersects(other.getRegion()))) {
                            if (other instanceof MovableBall && breakableObject.isBroken((MovableBall) other)) {
                                PlayerManager playerManager = getPlayerManager();
                                List<Player> players = playerManager.getPlayers();
                                for (Player player : players) {
                                    if (player.getId() == 1) {
                                        ((BreakableBox) breakableObject).updatePlayerScore(player);
                                    }
                                }
                                remove(object);
                            }
                        }
                    }
                }
            }
        }
    }

    private void drawScoreBoard(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));

        int yPosition = 20;

        List players = playerManager.getPlayers();
        for (Object object : players) {
            g.drawString("Player " + ((Player)object).getId() + " Score: " + ((Player)object).getScore(), 20, yPosition);
            yPosition += 30;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 스코어판 그리기
        drawScoreBoard(g);
    }
    

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            move();
            try {
                Thread.sleep(getDT());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        maxMoveCount = count;
    }

}