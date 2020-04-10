package com.nh006220.VirusGame;

import static java.lang.Math.atan2;
import static java.lang.Math.floor;

public class Player extends GameObject {
    public Player(GameView gameView, float x, float y) {
        super(gameView, x, y);
        setSpeedX(10);
    }


    public void getNewHeading(float x, float y) {
        int speed = 10;
        float diffX = this.getX() - x;
        float diffY = this.getY() - y;
        float magnitude = (float)Math.sqrt((diffX * diffX + diffY * diffY));
        this.setSpeedX(-diffX/magnitude * speed);
        this.setSpeedY(-diffY/magnitude * speed);
    }
}
