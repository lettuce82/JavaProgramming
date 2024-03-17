package com.example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CannonShape extends MovableBox {
    private int cannonAngle = 45; // 초기 각도 설정
    int cnBodyX = 0; //300
    int cnBodyY = 0; //250
    int cannonX = 0;
    int cannonY = 0;

    int cnBodyWidth = 0; //100
    int cnBodyHeight = 0; //20
    int cnPoint = 0; //20

    Point cannonPoint;

    public CannonShape(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        cnBodyX = x;
        cnBodyY = y;
        cnBodyWidth = width;
        cnBodyHeight = height;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // 대포통 그리기
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(getBounds().getMinX(), getBounds().getMinY(), cnBodyWidth, cnBodyHeight);

        // 대포통 위쪽 그리기
        int cnHeadWidth = 80;
        int cnHeadHeight = 20;
        int cnHeadX = getBounds().getMinX() + (cnBodyWidth - cnHeadWidth) / 2;
        int cnHeadY = getBounds().getMinY() - cnHeadHeight;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(cnHeadX, cnHeadY, cnHeadWidth, cnHeadHeight, 15, 15);

        // 포 그리기 (각도 조절 적용)
        cannonX = cnHeadX + cnHeadWidth / 2; // 포의 x 좌표를 대포통의 중앙으로 설정합니다.
        cannonY = cnHeadY;
        cannonPoint = getRotatedEndPoint(cannonX, cannonY, 100, cannonAngle);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(cannonX, cannonY, cannonPoint.x, cannonPoint.y);
        g2d.setStroke(new BasicStroke());

        // 대포통 아래쪽 가장자리 그리기
        g2d.setColor(Color.BLACK);
        g2d.drawLine(getBounds().getMinX(), getBounds().getMinY(), getBounds().getMinX() + cnBodyWidth, getBounds().getMinY());
    }

    public int getCannonX() {
        return this.cannonX;
    }

    public int getCannonY() {
        return this.cannonY;
    }

    public Point getPoint() {
        return this.cannonPoint;
    }

    // 각도 설정 메서드
    public void setCannonAngle(int angle) {
        this.cannonAngle = angle;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    private Point getRotatedEndPoint(int startX, int startY, int length, double angleInDegrees) {
        double angleInRadians = Math.toRadians(angleInDegrees);
        int rotatedX = (int) (startX + length * Math.cos(angleInRadians));
        int rotatedY = (int) (startY - length * Math.sin(angleInRadians)); // 여기서 y값에 -를 추가합니다.
        return new Point(rotatedX, rotatedY);
    }
}