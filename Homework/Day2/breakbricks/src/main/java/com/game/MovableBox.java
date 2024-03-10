package com.game;

import java.awt.Color;

public class MovableBox extends PaintableBox implements Movable {
    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;

    final Vector motion = new Vector();

    public MovableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public MovableBox(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAULT_COLOR);
    }

    public Vector getMotion() {
        return motion;
    }

    public double getDX() {
        return motion.getDX();
    }

    public double getDY() {
        return motion.getDY();
    }

    public void setDX(double dx) {
        motion.setDX(dx);
    }

    public void setDY(double dy) {
        motion.setDY(dy);
    }

    public void move() {
        double newX = getRegion().getCenterX() + getDX();
        double newY = getRegion().getCenterY() + getDY();

        moveTo(newX, newY);
    }

    public void moveTo(double x, double y) {
        setX(x);
        setY(y);
    }
}