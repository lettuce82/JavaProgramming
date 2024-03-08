package com.example.nhnacademy;


import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBall extends MovableBall implements Bounded {

    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;

    BoundedBall(int x, int y, int radius, Color color) {
        super(x,y,radius,color);

        bounds = new Rectangle(x - (int) getRegion().getWidth() / 2, y - (int) getRegion().getWidth() / 2, (int) getRegion().getWidth() / 2, (int) getRegion().getWidth() / 2);
    }

    Rectangle bounds;

    Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    boolean isOutOfBounds() {
        return (getX() - getRadius() < getBounds().getMinX() || getX() + getRadius() > getBounds().getMaxX() || getY() - getRadius() < getBounds().getMinY() || getY() + getRadius() > getBounds().getMaxY() );
    }

    @Override
    public void move() {
        super.move();
    }

    public void bounce(Regionable otherBall) {
        Rectangle intersetction = getRegion().intersection(otherBall.getRegion());
        
        if (getRegion().getHeight() != intersetction.getHeight()) {
            setDY(-getDY());
        } 

        if (getRegion().getWidth() != intersetction.getWidth()) {
            setDX(-getDX());
        } 
    }
}
