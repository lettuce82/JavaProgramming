package com.game;

public class MovableWorld extends World {
    static final int DEFAULT_DT = 10;
    int moveCount;
    int maxMoveCount = 0;
    int dt = DEFAULT_DT;

    public void setDT(int dt) {
        if (dt < 0) {
            throw new IllegalArgumentException();
        }
        this.dt = dt;
    }

    public int getDT() {
        return dt;
    }

    public void reset() {
        moveCount = 0;
    }

    public void move() {
        if ((getMaxMoveCount() == 0) || (getMoveCount() < getMaxMoveCount())) {
            for (int i = 0; i < getCount(); i++) {
                Regionable object = get(i);
                if (object instanceof Movable) {
                    ((Movable) object).move();
    
                    if (object instanceof Bounded) {
                        Bounded boundedObject = (Bounded) object;
                        for (int j = 0; j < getCount(); j++) {
                            Regionable other = get(j);
    
                            if ((object != other) && (object.getRegion().intersects(other.getRegion()))) {
                                if (other instanceof Bounded) {
                                    boundedObject.bounce(other);
                                }
                            }
                        }
                    }
    
                    // Breakable 
                    if (object instanceof Breakable) {
                        Breakable breakableObject = (Breakable) object;
                        for (int j = 0; j < getCount(); j++) {
                            Regionable other = get(j);
                            if ((object != other) && (object.getRegion().intersects(other.getRegion()))) {
                                if (other instanceof MovableBall && breakableObject.isBroken((MovableBall) other)) {
                                    remove(object);
                                }
                            }
                        }
                    }
                }
            }
            moveCount++;
            repaint();
        }
    }
    

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            move();
            try {
                System.out.println("쉼");
                Thread.sleep(getDT());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        maxMoveCount = count;
    }

}