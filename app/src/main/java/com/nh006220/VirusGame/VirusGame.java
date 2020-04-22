package com.nh006220.VirusGame;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirusGame extends GameThread {
    private Player player;
    private List<Enemy> enemies;

    public VirusGame(GameView gameView) {
        super(gameView);

        player = new Player(gameView, mCanvasHeight / 2, mCanvasWidth / 2, 150);

        enemies = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            Random r = new Random();
            enemies.add(new Enemy(gameView,
                    r.nextInt((500 - (-500)) + 1) + (-500),
                    r.nextInt((500 - (-500)) + 1) + (-500),
                    100));
        }
    }

    @Override
    protected void updateGame(float secondsElapsed) {

        player.update(secondsElapsed);

        if (!player.isInBounds(mCanvasWidth, mCanvasHeight)) {
            //setState(GameThread.STATE_LOSE);
        }

        for (Enemy e : enemies) {
            if (isColliding(e) && e.isVisible()) {
                e.setVisible(false);
                updateScore(1);
                player.increaseScale(enemies.size() * 0.2);
            }
        }
    }

    //checks for collisions with player
    private boolean isColliding(GameObject g) {
        return Rect.intersects(player.getBounds(), g.getBounds());
    }

    @Override
    protected void doDraw(Canvas canvas) {
        super.doDraw(canvas);

        canvas.translate(mCanvasWidth / 2 - player.getX(), mCanvasHeight / 2 - player.getY());

        player.draw(canvas);

        for (Enemy e : enemies) {
            e.draw(canvas);
        }
    }

    @Override
    protected void actionOnTouch(float x, float y) {
        player.getNewHeading((mCanvasWidth / 2) - x, (mCanvasHeight / 2) - y);
    }

    @Override
    public void setupBeginning() {

    }
}
