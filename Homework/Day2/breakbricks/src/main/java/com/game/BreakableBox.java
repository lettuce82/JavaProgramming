package com.game;

import java.awt.Color;

public class BreakableBox extends BoundedBox implements Breakable {

    private int hitCount = 0;
    private int maxHits = 0;

    public BreakableBox(int x, int y, int width, int height, int maxHits) {
        super(x, y, width, height, Color.BLACK);
        this.maxHits = maxHits;
        setColorByMaxHits();
    }

    public BreakableBox(int x, int y, int width, int height, Color color, int maxHits) {
        super(x, y, width, height, color);
        this.maxHits = maxHits;
        setColorByMaxHits();
    }

    public int getTouchedCount() {
        return hitCount;
    }

    public int getMaxTouchedCount() {
        return maxHits;
    }

    public void setTouchedCount(int count) {
        hitCount = count;
    }

    @Override
    public boolean isBroken(MovableBall movableBall) {
        if (getRegion().intersects(movableBall.getRegion())) {
            hitCount++;
            setColorByMaxHits();
            return hitCount >= maxHits;
        }
        return false;
    }

    public void updatePlayerScore(Player player) {
        player.setScore(player.getScore() + maxHits); // maxHits 만큼 스코어 증가
    }

    private void setColorByMaxHits() {
        switch (maxHits) {
            case 1:
                setColor(Color.BLUE);
                break;
            case 2:
                setColor(Color.GREEN);
                break;
            case 3:
                setColor(Color.YELLOW);
                break;
            case 4:
                setColor(Color.ORANGE);
                break;
            case 5:
                setColor(Color.RED);
                break;
            default:
                break;
        }
    }
}
