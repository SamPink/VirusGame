package com.nh006220.VirusGame;

public class Enemy extends GameObject{
    private int value;
    public Enemy(GameView gameView, float x, float y, int scale) {
        super(gameView, x, y, scale);
        int value = 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}