package com.game;

import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBall extends MovableBall implements Bounded {

    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;
    Rectangle bounds;
    Rectangle frameBounds;

    BoundedBall(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
        bounds = new Rectangle(x - (int) getRegion().getWidth() / 2, y - (int) getRegion().getWidth() / 2, (int) getRegion().getWidth() / 2, (int) getRegion().getWidth() / 2);
    }

    BoundedBall(int x, int y, int radius) {
        this(x, y, radius, DEFAULT_COLOR);
    }


    Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    boolean isOutOfBounds() {
        return (getRegion().getMinX() < getBounds().getMinX() || getRegion().getMaxX() > getBounds().getMaxX() || getRegion().getMinY() < getBounds().getMinY() || getRegion().getMaxY() > getBounds().getMaxY() );
    }

    @Override
    public void move() {
        super.move();
    }

    public void bounce(Regionable otherBall) {
        // 객체의 이동 전 위치와 이동 후 위치를 비교하여 테두리에 닿았는지 확인
        int newX = (int) getRegion().getX();
        int newY = (int) getRegion().getY();
    
        // 테두리에 닿았을 경우에만 반동을 줌
        if (newX - getWidth() / 2< bounds.getMinX() || newX + getWidth() / 2 > bounds.getMaxX()) {
            setDX(-getDX());
        }
        if (newY - getWidth() / 2 < bounds.getMinY() || newY + getWidth() / 2 > bounds.getMaxY()) {
            setDY(-getDY());
        }
    }
}
