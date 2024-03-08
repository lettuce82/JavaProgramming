package com.example.nhnacademy;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.*; 



public class FramDemo01 {
    public static class MyCanvas extends JPanel {
            int y1 = 200;
            int y2 = 350;
            int x1 = 130;
            int x2 = 130;
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //System.out.println("paint mycanvas : " + getWidth() + ", " + getHeight());
            //g.setColor(Color.red);

            /*
            for (int i = 0; i < 10; i++) {
                g.drawRect(30 + i*40, 30 + i*40, 10 + i*10, 10 + i*10);
            }
            */
            
            g.drawLine(x1, y1, x2, y2); //아래직선
            //g.drawLine(x1 / 2, y1 / 2, (x1 + x2) / 2, y1); //왼쪽
            g.drawLine((x1 + x2 * 2) / 2, y1 / 2, (x1 + x2) / 2, y1); //오른쪽
            
        }
    }
    public static void main(String[] args) {
        // 1. Frame 만들기
        JFrame frame = new JFrame("Demo01");
        // 2. Frame의 크기를 지정한다.
        frame.setSize(1000, 800);
        // 3. Frame을 보여 준다
        // 4. Graphics 객체를 가져와 사각형을 그린다
        //frame.getGraphics().fillRect(50, 50, 100, 100);

        MyCanvas panel = new MyCanvas();
        frame.add(panel);
        frame.setVisible(true);

    }
}
