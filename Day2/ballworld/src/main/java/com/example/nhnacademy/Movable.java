package com.example.nhnacademy;

public interface Movable {
    /**
     * object에 단위 시간만큼 이동을 지시합니다.
     */
    public void move();

    public void setDX(int dx);

    public void setDY(int dy);

    public int getDX();

    public int getDY();
}
