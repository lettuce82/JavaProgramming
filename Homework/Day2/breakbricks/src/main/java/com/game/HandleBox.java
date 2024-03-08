package com.game;

import java.awt.Color;

public class HandleBox extends BoundedBox implements Movable {
    
    public HandleBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public HandleBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);

        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public static final int DEFAULT_DX = 0;
    public static final int DEFAULT_DY = 0;
}
