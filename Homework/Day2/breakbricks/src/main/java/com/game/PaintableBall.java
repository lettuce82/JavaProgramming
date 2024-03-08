package com.game;


import java.awt.Color;
import java.awt.Graphics;

public class PaintableBall extends Ball implements Paintable{
    public static final Color DEFAULT_COLOR = Color.BLACK;
    Color color;

    public PaintableBall(int x, int y, int radius) {
        this(x, y, radius, DEFAULT_COLOR);
    }

    public PaintableBall(int x, int y, int radius, Color color) {
        super(x, y, radius);

        if (color == null) {
            throw new IllegalArgumentException();
        }

        this.color = color;
    }

    public Color getColor() {
        return color;
    }

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
        g.fillOval((int)getRegion().getMinX(), (int)getRegion().getMinY(), getWidth(), getWidth());
        g.setColor(Color.GRAY);
        //영역 표시해보기
        g.drawRect((int)getRegion().getMinX(), (int)getRegion().getMinY(), (int)getRegion().getWidth(),
                (int) getRegion().getHeight());

        g.setColor(originalColor);
    }
}