// Vector.java
package com.game;

public class Vector {
    // dx , dy 변수
    double dx;
    double dy;

    public Vector() {
        dx = 0;
        dy = 0;
    }

    public Vector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDX() {
        return dx;
    }

    public double getDY() {
        return dy;
    }

    public void setDX(double dx) {
        this.dx = dx;
    }

    public void setDY(double dy) {
        this.dy = dy;
    }

    public void turnDX() {
        setDX(-getDX());
    }

    public void turnDY() {
        setDY(-getDY());
    }

    public void set(double dx, double dy) {
        setDX(dx);
        setDY(dy);
    }

    public void set(Vector other) {
        setDX(other.getDX());
        setDY(other.getDY());
    }

    public void add(Vector other) {
        set(getDX() + other.getDX(), getDY() + other.getDY());
    }

    public void subtract(Vector other) {
        set(getDX() - other.getDX(), getDY() - other.getDY());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Vector) && (((Vector) other).getDX() == getDX())
                && (((Vector) other).getDY() == getDY());
    }
}