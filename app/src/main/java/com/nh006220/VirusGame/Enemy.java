package com.nh006220.VirusGame;

/**
 * static enemy class
 * only needs to store its position on screen
 */
public class Enemy extends GameObject {
    private int value;

    /**
     * constructor
     * @param gameView view enemy is added too
     * @param x x position on screen
     * @param y y position on screen
     * @param scale siz of image rendered
     */
    public Enemy(GameView gameView, float x, float y, int scale) {
        super(gameView, x, y, scale);
    }

    @Override
    public void update(float sec) {
        //update does not need to do anything
        super.update(sec);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean doesDamage() {
        //no damage is done by this enemy so always return false
        return false;
    }
}