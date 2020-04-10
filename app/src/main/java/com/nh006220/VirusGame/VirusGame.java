package com.nh006220.VirusGame;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

public class VirusGame extends GameThread {
   private Player player;
   private List<Enemy> enemies;

    public VirusGame(GameView gameView) {
        super(gameView);

        player = new Player(gameView, mCanvasHeight/2,mCanvasWidth/2);

        enemies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            enemies.add(new Enemy(gameView, i*20,i*20));
        }
    }

    @Override
    public void setupBeginning() {
    }

    @Override
    protected void updateGame(float secondsElapsed) {

        player.update(secondsElapsed);

        if(!player.isInBounds(mCanvasWidth, mCanvasHeight)){
            player.setX(100);
            player.setY(100);
        }

        for (Enemy e: enemies) {
            isColliding(e);
        }
    }

    //checks for collisions with player
    private boolean isColliding(GameObject g) {
        Rect boundsPlayer = new Rect(
                (int) player.getX(),
                (int) player.getY(),
                (int)player.getX()+player.getImage().getWidth(),
                (int)player.getY()+player.getImage().getHeight());

        Rect boundsg = new Rect(
                (int) g.getX(),
                (int) g.getY(),
                (int)g.getX()+g.getImage().getWidth(),
                (int)g.getY()+g.getImage().getHeight());

        if(Rect.intersects(boundsPlayer,boundsg)){
            System.out.println("collision");
        }

        return false;
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }

    @Override
    public void doStart() {
        super.doStart();
    }

    @Override
    public void run() {
        super.run();
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
        System.out.println("hey");
        player.getNewHeading(x, y);
//        player.setX(x);
//        player.setY(y);
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
