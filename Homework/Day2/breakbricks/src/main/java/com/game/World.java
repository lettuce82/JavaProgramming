package com.game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.LinkedList;

import javax.swing.JPanel;

public class World extends JPanel implements MouseMotionListener {
    List<Regionable> regionableList = new LinkedList<>();

    public World() {
        super();
        addMouseMotionListener(this);
    }

    /**
     *
     * @param ball
     * @throw IllegalArgumentException 공간을 벗어나거나, null인 경우, 볼간 충돌된 경우
     */
    public void add(Regionable object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
        //생성된 객체의 영역이 겹치면 예외처리
        for (Regionable existObject : regionableList) {
            if (object.getRegion().intersects(existObject.getRegion())) {
                throw new IllegalArgumentException();
            }
        }
        regionableList.add(object);
    }

    public void remove(Regionable object) {
        regionableList.remove(object);
    }

    @Override
    public void remove(int index) {
        regionableList.remove(index);
    }

    public int getCount() {
        return regionableList.size();
    }

    public Regionable get(int index) {
        return regionableList.get(index);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Regionable object : regionableList) {
            if (object instanceof Paintable) {
                ((Paintable) object).paint(g);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Regionable regionable : regionableList) {
            if (regionable instanceof HandleBox) {
                ((Movable)regionable).moveTo(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}