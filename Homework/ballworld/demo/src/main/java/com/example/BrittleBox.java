package com.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.example.interfaces.Brittle;
import com.example.interfaces.HitInterface;

public class BrittleBox extends PaintableBox implements Brittle, HitInterface {
    public static final Color DEFAULT_COLOR = Color.BLACK;
    String text = "";

    public BrittleBox(int x, int y, int width, int height) {
        super(x, y, width, height, DEFAULT_COLOR);
    }

    public BrittleBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public BrittleBox(int x, int y, int width, int height, Color color, String text) {
        super(x, y, width, height, color);
        this.text = text;
    }

    // BrittleBox 클래스 내 paint 메소드 수정
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 텍스트를 그리기 전에 상자의 크기를 고려하여 위치 조정
        Font font = new Font("Arial", Font.BOLD, 15);
        g.setFont(font);
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getHeight();

        // 텍스트가 상자의 크기를 벗어나지 않도록 위치 조정
        int textX = getX() + (getWidth() - textWidth) / 2 - 45;
        int textY = getY() + (getHeight() + textHeight) / 2 - 35;

        // 텍스트 그리기 (상자 색상과 대비되는 색상 사용)
        Color textColor = (getColor().equals(Color.BLACK)) ? Color.WHITE : Color.BLACK;
        g.setColor(textColor);
        g.drawString(text, textX, textY);
    }


}
