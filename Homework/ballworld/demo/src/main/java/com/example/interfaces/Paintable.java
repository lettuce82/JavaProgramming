package com.example.interfaces;

import java.awt.Color;
import java.awt.Graphics;

public interface Paintable {
    public void setColor(Color color);

    public Color getColor();

    public void paint(Graphics g);
}
