package com.game;

import java.awt.Color;
import java.awt.Graphics;

public class PaintableBox extends Box implements Paintable {
    public static final Color DEFAULT_COLOR = Color.BLACK;
    Color color;

    public PaintableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    public PaintableBox(int x, int y, int width, int height) {
        this(x, y, width, height, DEFAULT_COLOR);
    }
    
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public void paint(Graphics g) {
        if (g == null) {
            throw new IllegalArgumentException();
        }
    
        Color originalColor = g.getColor();
    
        g.setColor(getColor());
        g.fillRect((int)getRegion().getMinX(), (int)getRegion().getMinY(), getWidth(), getHeight());
        // 영역 표시해보기
        g.setColor(Color.GRAY);
        g.drawRect((int)getRegion().getX(), (int)getRegion().getY(), getWidth(), getHeight());
    
        g.setColor(originalColor);
    }

}
