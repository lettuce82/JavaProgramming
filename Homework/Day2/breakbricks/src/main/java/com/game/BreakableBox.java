package com.game;

import java.awt.Color;
import java.awt.Rectangle;

public class BreakableBox extends BoundedBox implements Breakable {

    static int hitCount = 0;
    private int maxHits = 0;

    public BreakableBox(int x, int y, int width, int height, Color color, int maxHits) {
        super(x, y, width, height, color);
        this.maxHits = maxHits;
    }

    public BreakableBox(int x, int y, int width, int height, int maxHits) {
        this(x, y, width, height, DEFAULT_COLOR, maxHits);
    }

    public int getTouchedCount() {
        return this.hitCount;
    }

    public void setTochedCount(int count) {
        this.hitCount = count;
    }

    public int getMaxTouchedCount() {
        return this.maxHits;
    }
    
    public boolean isBroken(MovableBall movableBall) {
        if (getRegion().intersects(movableBall.getRegion())) {
            setTochedCount(++hitCount);
            if (getTouchedCount() >= getMaxTouchedCount()) {
                return true;
            }
        }
        return false;
    }
}
