package com.game;

import java.awt.Rectangle;

public interface Regionable {
    /*
     * getLocaton() 얻을 수 있어서 임시 삭제 -> getLocation() 임시 추가
    public int getX();
    public int getY();
     */
    public Point getLocation();
    public Rectangle getRegion();
    public int getId();
}

