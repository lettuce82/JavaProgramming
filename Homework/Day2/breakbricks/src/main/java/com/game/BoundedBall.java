package com.game;

import java.awt.Color;
import java.awt.Rectangle;

public class BoundedBall extends MovableBall implements Bounded {

    static final int FRAME_WIDTH = 500;
    static final int FRAME_HEIGHT = 400;

    BoundedBall(int x, int y, int radius, Color color) {
        super(x,y,radius,color);
        bounds = new Rectangle(x - (int) getRegion().getWidth() / 2, y - (int) getRegion().getWidth() / 2, (int) getRegion().getWidth() / 2, (int) getRegion().getWidth() / 2);
    }

    BoundedBall(int x, int y, int radius) {
        this(x,y,radius,Color.BLACK);
    }

    Rectangle bounds;

    Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isOutOfBounds() {
        return (getX() - getRadius() < getBounds().getMinX() || getX() + getRadius() > getBounds().getMaxX() || getY() - getRadius() < getBounds().getMinY() || getY() + getRadius() > getBounds().getMaxY() );
    }

    @Override
    public void move() {
        super.move();
    }

    public void bounce(Regionable other) {
        Rectangle intersection = getRegion().intersection(other.getRegion());
        
        // 만약 충돌이 감지되었다면
        if (!intersection.isEmpty()) {
            double dx = getDX();
            double dy = getDY();
            
            // 충돌이 발생한 축에 따라 공의 이동 방향을 조절
            if (intersection.getWidth() < intersection.getHeight()) {
                // 충돌이 x 축 방향으로 발생한 경우
                setDX(-dx); // x 축 이동 방향 반전
            } else {
                // 충돌이 y 축 방향으로 발생한 경우
                setDY(-dy); // y 축 이동 방향 반전
            }
        }
    }
}
