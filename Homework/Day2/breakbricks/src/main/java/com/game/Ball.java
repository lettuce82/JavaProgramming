package com.game;

import java.awt.Rectangle;

public class Ball implements Regionable {
    static int getRegionCallCount = 0;
    static int count = 0;
    int id = ++count;
    Rectangle region;

    public Ball(int x, int y, int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 0보다 커야 합니다.");
        }

        if ((x + (long) radius > Integer.MAX_VALUE)
                || (x - (long) radius < Integer.MIN_VALUE)
                || (y + (long) radius > Integer.MAX_VALUE)
                || (y - (long) radius < Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("볼이 정수 공간을 벗어납니다.");
        }

        region = new Rectangle(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public Point getLocation() {
        return new Point((int)region.getCenterX(), (int)region.getCenterY());
    }
    
    int getWidth() {
        return (int)region.getWidth();
    }

    int getHeight() {
        return (int)region.getHeight();
    }


    void setX(int x) {
        region.setLocation(x - getWidth() / 2, getLocation().getY() - getHeight() / 2);
    }

    void setY(int y) {
        region.setLocation(getLocation().getX() - getWidth() / 2, y - getHeight() / 2);
    }

    public Rectangle getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", getRegion().getX(), getRegion().getY(), getWidth());
    }

    public int getId() {
        return id;
    }
}