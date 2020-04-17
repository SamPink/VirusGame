package com.nh006220.VirusGame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirusGame extends GameThread {
   private Player player;
   private List<Enemy> enemies;

    public VirusGame(GameView gameView) {
        super(gameView);

        player = new Player(gameView, mCanvasHeight/2,mCanvasWidth/2, 150);

        enemies = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            System.out.println(mCanvasHeight);
            System.out.println(mCanvasWidth);
            enemies.add(new Enemy(gameView, r.nextInt(1000),r.nextInt(1000), 100));
        }
    }

    @Override
    public void setupBeginning() {
    }

    @Override
    protected void updateGame(float secondsElapsed) {

        player.update(secondsElapsed);

        if(!player.isInBounds(mCanvasWidth, mCanvasHeight)){
            //setState(GameThread.STATE_LOSE);
        }

        for (Enemy e: enemies) {
            if(isColliding(e) && e.isVisible()){
                e.setVisible(false);
                updateScore(1);
                player.increaseScale(enemies.size()*0.2); //TODO increment it by a factor of the scale of the enemy
            }
        }
    }

    //checks for collisions with player
    private boolean isColliding(GameObject g) {

        if(Rect.intersects(player.getBounds(),g.getBounds())) return true;

        return false;
    }



    @Override
    public void setSurfaceSize(int width, int height) {
        super.setSurfaceSize(width, height);
    }

    @Override
    protected void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        player.draw(canvas);

        for (Enemy e: enemies) {
            e.draw(canvas);
        }
    }

    @Override
    public boolean onTouch(MotionEvent e) {
        return super.onTouch(e);
    }

    @Override
    protected void actionOnTouch(float x, float y) {
        player.getNewHeading(x, y);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);
    }

    @Override
    protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {
        super.actionWhenPhoneMoved(xDirection, yDirection, zDirection);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void unpause() {
        super.unpause();
    }

    @Override
    public void setState(int mode) {
        super.setState(mode);
    }

    @Override
    public void setState(int mode, CharSequence message) {
        super.setState(mode, message);
    }

    @Override
    public void setSurfaceHolder(SurfaceHolder h) {
        super.setSurfaceHolder(h);
    }

    @Override
    public boolean isRunning() {
        return super.isRunning();
    }

    @Override
    public void setRunning(boolean running) {
        super.setRunning(running);
    }

    @Override
    public int getMode() {
        return super.getMode();
    }

    @Override
    public void setMode(int mMode) {
        super.setMode(mMode);
    }

    @Override
    public void setScore(long score) {
        super.setScore(score);
    }

    @Override
    public float getScore() {
        return super.getScore();
    }

    @Override
    public void updateScore(long score) {
        super.updateScore(score);
    }

    @Override
    protected CharSequence getScoreString() {
        return super.getScoreString();
    }
}
