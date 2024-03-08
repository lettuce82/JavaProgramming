package com.nhnacademy;

import javax.swing.*;
import java.awt.*;

public class drawTree extends JFrame {
    private int startX;
    private int startY;
    private int angle;
    private int length;
    private int rotate;
    private int growth;

    public drawTree(int width, int height, int x, int y,
                      int angle, int length, int rotate, int growth) {
        this.startX = x;
        this.startY = y;
        this.angle = angle;
        this.length = length;
        this.rotate = rotate;
        this.growth = growth;

        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void branch(Graphics g, int startX, int startY, int degree, int length) {
        if (length > 1) {
            int endX = startX - (int) (length * Math.cos(Math.toRadians(degree)));
            int endY = startY - (int) (length * Math.sin(Math.toRadians(degree)));

            g.drawLine(startX, startY, endX, endY);

            branch(g, endX, endY, degree - rotate, length * growth / 100);
            branch(g, endX, endY, degree + rotate, length * growth / 100);
        }
    }

    @Override
    public void paint(Graphics g) {
        branch(g, startX, startY, angle, length);
    }

    public static void main(String[] args) {
        drawTree tree = new drawTree(500, 500, 250, 450, 90, 100, 30, 75);
        tree.setVisible(true);
    }
}
