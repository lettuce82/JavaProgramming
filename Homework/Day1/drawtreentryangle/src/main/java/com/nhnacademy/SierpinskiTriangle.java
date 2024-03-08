package com.nhnacademy;

import javax.swing.*;
import java.awt.*;

public class SierpinskiTriangle extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private void SierpinskiTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int depth) {
        if (depth == 0) {
            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};
            g.drawPolygon(xPoints, yPoints, 3);
        } else {
            int x12 = (x1 + x2) / 2;
            int y12 = (y1 + y2) / 2;
            int x23 = (x2 + x3) / 2;
            int y23 = (y2 + y3) / 2;
            int x31 = (x3 + x1) / 2;
            int y31 = (y3 + y1) / 2;

            SierpinskiTriangle(g, x1, y1, x12, y12, x31, y31, depth - 1);
            SierpinskiTriangle(g, x12, y12, x2, y2, x23, y23, depth - 1);
            SierpinskiTriangle(g, x31, y31, x23, y23, x3, y3, depth - 1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        SierpinskiTriangle(g, WIDTH / 2, 50, 50, HEIGHT - 50, WIDTH - 50, HEIGHT - 50, 6); // 6은 재귀 깊이
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.getContentPane().add(new SierpinskiTriangle());
        frame.setVisible(true);
    }
}
