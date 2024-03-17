package com.example.interfaces;

import com.example.Bounds;

public interface Bounded {

    Bounds getBounds();

    int getHeight();

    int getMinY();

    int getMaxY();

    int getWidth();

    int getMinX();

    int getMaxX();

}
