package com.game;

public interface Movable {
    /**
     * object에 단위 시간만큼 이동을 지시합니다.
     */
    public void move();

    public void moveTo(double x, double y);

    public void setDX(double dx);

    public void setDY(double dy);

    public double getDX();

    public double getDY();
}
