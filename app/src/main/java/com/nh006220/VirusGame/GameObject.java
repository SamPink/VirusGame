package com.nh006220.VirusGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * base object class that stores the position of object on canvas
 * stores the speed in both directions
 * stores the image rendered to the screen
 */
public abstract class GameObject {
    private int size; //size is used to x and y of object when drawn. this is increased for player
    private Bitmap image;
    private float x, y;
    private float speedX, speedY;
    private boolean visible; //when true object is drawn to canvas

    /**
     * constructor for creating game object
     * @param gameView view object will be drawn to
     * @param x position on screen
     * @param y position on screen
     * @param size size in pixels
     */
    public GameObject(GameView gameView, float x, float y, int size) {
        this.image = BitmapFactory.decodeResource(gameView.getContext().getResources(), R.drawable.virus); //get object from resource
        this.x = x;
        this.y = y;
        this.speedX = 0;
        this.speedY = 0;
        this.size = size;
        this.visible = true;

        scale(size);// set the size
    }

    /**
     * speed of object is a factor of time and speed X or Y
     * @param sec
     */
    public void update(float sec) {
        //Move the ball's X and Y using the speed (pixel/sec)
        this.x = this.x + sec * this.speedX;
        this.y = this.y + sec * this.speedY;
    }

    /**
     * only draw when viable is true
     * @param canvas
     */
    public void draw(Canvas canvas) {
        if (visible) {
            canvas.drawBitmap(this.getImage(), this.getX() - this.getImage().getWidth() / 2, this.getY() - this.getImage().getHeight() / 2, null);
        }
    }

    /**
     *
     * @param size
     */
    public void scale(int size) {
        this.size = size;
        this.image = Bitmap.createScaledBitmap(this.image, this.size, this.size, false);
    }

    /**
     * returns true if not colliding
     *
     * @param maxX
     * @param maxY
     * @return
     */
    public boolean isInBounds(int maxX, int maxY) {
        if (this.y >= maxY) {
            System.out.println("top collision");
            return false;
        }
        if (this.y <= 0) {
            System.out.println("bottom collision");
            return false;
        }
        if (this.x >= maxX) {
            System.out.println("right side collision");
            return false;
        }
        if (this.x <= 0) {
            System.out.println("left side collision");
            return false;
        }

        return true;
    }

    public void increaseScale(double v) {
        this.size += v;
        scale(size);
        speedX++;
        speedY++;
    }

    public Rect getBounds() {
        return new Rect(
                (int) this.getX(),
                (int) this.getY(),
                (int) this.getX() + this.size / 2,
                (int) this.getY() + this.size / 2);
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
