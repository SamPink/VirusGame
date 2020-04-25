package com.nh006220.VirusGame;

public class Enemy extends GameObject {
    private int value;

    public Enemy(GameView gameView, float x, float y, int scale) {
        super(gameView, x, y, scale);
        int value = 1;
    }

    @Override
    public void update(float sec) {
        super.update(sec);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean doesDamage() {
        return false;
    }
}