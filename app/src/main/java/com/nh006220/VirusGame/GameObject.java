package com.nh006220.VirusGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.nh006220.VirusGame.R;

//TODO add a draw method

public abstract class GameObject {
    private Bitmap image;
    private float x,y;
    private float speedX,speedY;

    public GameObject(GameView gameView, float x, float y) {
        //TODO all objects are now a small red ball, this should be a parameter
        this.image = BitmapFactory.decodeResource(gameView.getContext().getResources(), R.drawable.small_red_ball);
        this.x = x;
        this.y = y;
        this.speedX = 0;
        this.speedY = 0;
    }

    public void update(float sec){
        //Move the ball's X and Y using the speed (pixel/sec)
        this.x = this.x+ sec* this.speedX;
        this.y = this.y + sec* this.speedY;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(this.getImage(), this.getX() - this.getImage().getWidth() / 2, this.getY() - this.getImage().getHeight() / 2, null);
    }

    /**
     * returns true if not colliding
     * @param maxX
     * @param maxY
     * @return
     */
    public boolean isInBounds(int maxX, int maxY){
        if(this.y >= maxY) {
            System.out.println("top collision");
            return false;
        }
        if(this.y <= 0){
            System.out.println("bottom collision");
            return false;
        }
        if(this.x >= maxX){
            System.out.println("right side collision");
            return false;
        }
        if(this.x <= 0){
            System.out.println("left side collision");
            return false;
        }

        return true;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
