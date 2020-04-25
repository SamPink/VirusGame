package com.nh006220.VirusGame;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;


public class VirusGame extends GameThread {
    private Player player;
    private List<Level> levels;
    private GameState game;
    private Enemy cl;
    private DatabaseReference mDatabase;

    public VirusGame(GameView gameView, int value) {
        super(gameView);

        player = new Player(gameView, mCanvasHeight / 2, mCanvasWidth / 2, 150);

        setGameMode(value);

        if (getGameMode() == 2) {
            //game mode 2 is a free game
            game = new FreeGame(gameView, new Map(1500,1500));
        } else {
            levels = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                levels.add(new Level(gameView,
                        new Map(500,500),
                        i+1,
                        10*(i+1))
                );
            }
            game = levels.get(0);
        }
    }

    /**
     * default constructor starts in level game
     * @param gameView
     */
    public VirusGame(GameView gameView) {
        super(gameView);
        setGameMode(1);
    }

    @Override
    protected void updateGame(float secondsElapsed) {

        player.update(secondsElapsed);

        game.update(secondsElapsed);

        double close = 999999;//set max value
        for (Enemy e : game.getEnemies()) {
            //TODO fix bounding box
            if (isColliding(e) && e.isVisible()) {
                if(!game.onCollision(e)){
                    //colliding with enemy on screen
                    updateScore(1);
                    player.increaseScale(e.getSize() * 0.05);
                    System.out.println(game.getRemainingEnemies() + "left of " + game.getEnemiesCount());
                }else{
                    endGame();
                }
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

        if (game.getRemainingEnemies() == 0 && getGameMode() == 1) {
            setState(GameThread.STATE_WIN);
            game = levels.get(game.getLevelNumber() + 1);

            System.out.println(game.getLevelNumber());
        }
    }

    private void endGame() {
        //Save score to high score
        mDatabase = FirebaseDatabase.getInstance().getReference("highScore");
        mDatabase.setValue(getScore());

        setState(GameThread.STATE_LOSE);
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

        for (Enemy e : game.getEnemies()) {
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
