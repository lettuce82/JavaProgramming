package com.example.nhnacademy;


import java.awt.Color;
import java.awt.Graphics;

public class PaintableBox extends Box implements Paintable {
    Color color;

    public PaintableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    public PaintableBox(int x, int y, int width, int height) {
        this(x, y, width, height, Color.BLACK);
    }
    
    public Color getColor() {
        return this.color;
    }

    public void paint(Graphics g) {
        g.fillRect((int)getRegion().getX(), (int)getRegion().getY(), (int)getRegion().getWidth(), (int)getRegion().getHeight());
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
