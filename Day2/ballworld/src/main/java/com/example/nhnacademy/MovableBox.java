package com.example.nhnacademy;


import java.awt.Color;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovableBox extends PaintableBox implements Movable {
    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;

    final Vector motion = new Vector();
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public MovableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public MovableBox(int x, int y, int width, int height) {
        this(x, y, width, height, Color.BLACK);
    }

    public Vector getMotion() {
        return motion;
    }

    public int getDX() {
        return motion.getDX();
    }

    public int getDY() {
        return motion.getDY();
    }

    public void setDX(int dx) {
        motion.setDX(dx);
    }

    public void setDY(int dy) {
        motion.setDY(dy);
    }

    public void move() {
        int newX = getX() + getDX();
        int newY = getY() + getDY();

        moveTo(newX, newY);
        logger.trace("{} : {}, {}, {}, {}", getId(), getX(), getY(), getRegion().getX(), getRegion().getY());
    }

    public void moveTo(int x, int y) {
        setX(x);
        setY(y);
    }
}