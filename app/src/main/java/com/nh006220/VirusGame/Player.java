package com.nh006220.VirusGame;


public class Player extends GameObject {
    private int speed;

    public Player(GameView gameView, float x, float y, int size) {
        super(gameView, x, y, size);
        setSpeedX(10);
        speed = 100;
    }

    public void getNewHeading(float x, float y) {
        float diffX = this.getX() - x;
        float diffY = this.getY() - y;
        float magnitude = (float) Math.sqrt((diffX * diffX + diffY * diffY));
        this.setSpeedX(diffX / magnitude * speed);
        this.setSpeedY(diffY / magnitude * speed);
    }
}
