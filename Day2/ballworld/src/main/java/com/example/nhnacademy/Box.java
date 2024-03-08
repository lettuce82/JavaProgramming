package com.example.nhnacademy;


import java.awt.Rectangle;

public class Box implements Regionable{
    final Rectangle region;
    int x;
    int y;
    int width;
    int height;
    static int count = 0;
    int id = ++count;

    public Box(int x, int y, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("0보다 커야 합니다.");
        }

        if ((x + (long) width / 2 > Integer.MAX_VALUE)
            || (x - (long) width / 2 < Integer.MIN_VALUE)
            || (y + (long) width / 2 > Integer.MAX_VALUE)
            || (y - (long) width / 2 < Integer.MIN_VALUE)) {
                throw new IllegalArgumentException("공간을 벗어났습니다.");
        }
        region = new Rectangle(x, y, width, height);
    }

    Point getLocation() {
        return new Point((int)region.getCenterX(), (int)region.getCenterY());
    }

    int getWidth() {
        return (int)region.getWidth();
    }

    int getHeight() {
        return (int)region.getHeight();
    }

    void setX(int x) {
        region.setLocation(x - getWidth() / 2, getY() - getHeight() / 2);
    }

    void setY(int y) {
        region.setLocation(getX() - getWidth() / 2, y - getHeight() / 2);
    }

    public Rectangle getRegion() {
        return this.region;
    }

    public int getX() {
        return (int)region.getCenterX();
    }

    public int getY() {
        return (int)region.getCenterY();
    }

    @Override
    public int getId() {
        return id;
    }
}
