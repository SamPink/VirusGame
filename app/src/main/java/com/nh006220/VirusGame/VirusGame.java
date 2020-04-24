package com.nh006220.VirusGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class VirusGame extends GameThread {
    private Player player;
    private List<Level> levels;
    private Level level;
    private Enemy cl;

    public VirusGame(GameView gameView) {
        super(gameView);

        player = new Player(gameView, mCanvasHeight / 2, mCanvasWidth / 2, 150);

        levels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            levels.add(new Level(i+1, 5*i, 0, gameView, new Map(1000, 1000)));
        }

        level = levels.get(0);
    }

    @Override
    protected void updateGame(float secondsElapsed) {

        player.update(secondsElapsed);

        double close = 999999;//set max value
        for (Enemy e : level.getEnemies()) {
            //TODO fix bounding box
            if (isColliding(e) && e.isVisible()) {
                //colliding with enemy on screen
                e.setVisible(false);
                updateScore(1);
                player.increaseScale(e.getSize() * 0.05);

                System.out.println(level.getRemainingEnemies() + "left of " +level.getEnemiesCount());
            } else if (e.isVisible()) {
                /* calculate distance from player */
                double hypot = Math.hypot(Math.abs(e.getY() - player.getY()),
                        Math.abs(e.getX() - player.getX()));

                if (hypot < close) {
                    //get closest enemy to player
                    close = hypot;
                    cl = e;//stores the closest enemy
                }
            }
        }

        if(level.getRemainingEnemies() == 0){
            setState(GameThread.STATE_WIN);
            level = levels.get(level.getLevelNumber()+1);

            System.out.println(level.getLevelNumber());
        }
    }

    //checks for collisions with player
    private boolean isColliding(GameObject g) {
        return Rect.intersects(player.getBounds(), g.getBounds());
    }

    @Override
    protected void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        //move the canvas relative to change in direction so player stays in center
        canvas.translate(mCanvasWidth / 2 - player.getX(), mCanvasHeight / 2 - player.getY());

        player.draw(canvas);

        if (cl != null) {
            //angle between closest enemy and player
            float angle = (float) Math.toDegrees(Math.atan2(cl.getBounds().centerY() - player.getY(), cl.getBounds().centerX() - player.getX()));

            if (angle < 0) {
                angle += 360;
            }

            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(7);

            canvas.drawLine(player.getX(), player.getY(), cl.getX(), cl.getY(), paint);

            //draw box at closest enemy canvas.drawRect(cl.getBounds(), new Paint());
        }

        for (Enemy e : level.getEnemies()) {
            e.draw(canvas);
        }
    }

    @Override
    protected void actionOnTouch(float x, float y) {
        player.getNewHeading(x, y, mCanvasWidth, mCanvasHeight);
    }

    @Override
    public void setupBeginning() {

    }
}
