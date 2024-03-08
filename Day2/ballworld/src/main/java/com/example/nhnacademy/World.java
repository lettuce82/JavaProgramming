package com.example.nhnacademy;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Random;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class World extends JPanel implements MouseListener {
    List<Regionable> regionableList = new LinkedList<>();
    Logger logger = LogManager.getLogger(this.getClass().getSimpleName());

    public World() {
        super();

        addMouseListener(this);
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

        /*
         * 
        if ((object.getX() - object.getRegion().getWidth() / 2 < 0)
                || (object.getX() + object.getRegion().getWidth() / 2 > getWidth())
                || (object.getY() - object.getRegion().getWidth() / 2 < 0)
                || (object.getY() + object.getRegion().getWidth() / 2 > getHeight())) {
            throw new IllegalArgumentException();
        }
         */

        /*
         * 
        for (Regionable existObject : regionableList) {
            if (object.getRegion().intersects(existObject.getRegion())) {
                throw new IllegalArgumentException();
            }
        }
         */

        for (Regionable existObject : regionableList) {
            if (object.getRegion().intersects(existObject.getRegion())) {
                throw new IllegalArgumentException();
            }
        }
        regionableList.add(object);
    }

    public void remove(Ball ball) {
        regionableList.remove(ball);
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
    public void mouseClicked(MouseEvent e) {
        Random random = new Random();

        //add(new PaintableBox(e.getX(), e.getY(), 50, 50));
        
        BoundedBall ball = new BoundedBall(e.getX(), e.getY(), 20, Color.BLUE);
        ball.setDX(-10 + random.nextInt(20));
        ball.setDY(-10 + random.nextInt(20));

        add(ball);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}