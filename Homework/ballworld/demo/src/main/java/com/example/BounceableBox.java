package com.example;

import java.awt.Color;
import java.util.List;

import com.example.interfaces.Bounceable;
import com.example.interfaces.Bounded;

public class BounceableBox extends MovableBox implements Bounceable {

    public BounceableBox(Point location, int width, int height, Color color) {
        this(location.getX(), location.getY(), width, height, color);
    }

    public BounceableBox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void move(List<Bounded> boundedList) {
        super.move();

        for (Bounded bounded : boundedList) {
            if (bounded != this) {
                bounce(bounded);
            }
        }
    }

    @Override
    public void bounce(Bounded other) {
        if (!isCollision(other.getBounds())) {
            return;
        }

        Bounds intersection = getBounds().intersection(other.getBounds());
        Vector newMotion = getMotion();

        if ((getBounds().getHeight() != intersection.getHeight()) && (other.getHeight() != intersection.getHeight())) {
            double newY = getMinY() < other.getMinY() ? other.getMinY() - getHeight() / 2 : other.getMaxY() + getHeight() / 2;
            setLocation(new Point(getX(), (int)newY));
            newMotion.turnDY();
        }

        if ((getBounds().getWidth() != intersection.getWidth()) && (other.getWidth() != intersection.getWidth())) {
            double newX = getMinX() < other.getMinX() ? other.getMinX() - getWidth() / 2 : other.getMaxX() + getWidth() / 2;
            setLocation(new Point((int)newX, getY()));
            newMotion.turnDX();
        }

        if (!getMotion().equals(newMotion)) {
            setMotion(newMotion);
        }
    }
}