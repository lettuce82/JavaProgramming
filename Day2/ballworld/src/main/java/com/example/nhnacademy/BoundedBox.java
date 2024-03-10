package com.example.nhnacademy;


import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBox extends MovableBox implements Bounded {

    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;

    BoundedBox(int x, int y, int width, int height, Color color) {
        super(x,y,width,height,color);

        bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
    }

    BoundedBox(int x, int y, int width, int height) {
        this(x, y, width, height, Color.BLACK);
    }

    Rectangle bounds;

    Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    boolean isOutOfBounds() {
        return (getX() - getWidth() < getBounds().getMinX() || getX() + getWidth() > getBounds().getMaxX() || getY() - getWidth() < getBounds().getMinY() || getY() + getWidth() > getBounds().getMaxY() );
    }

    @Override
    public void move() {
        super.move();
    }

    public void bounce(Regionable other) {
        Rectangle intersetction = getRegion().intersection(other.getRegion());
        
        if (getRegion().getHeight() != intersetction.getHeight()) {
            setDY(-getDY());
        } 

        if (getRegion().getWidth() != intersetction.getWidth()) {
            setDX(-getDX());
        } 
    }
}
