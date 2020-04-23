package com.nh006220.VirusGame;

import android.graphics.Canvas;
import android.graphics.Rect;


public class VirusGame extends GameThread {
    private Player player;
    private Level level;

    public VirusGame(GameView gameView) {
        super(gameView);

        player = new Player(gameView, mCanvasHeight / 2, mCanvasWidth / 2, 150);

        level = new Level(1, 100, 0, gameView);
    }

    @Override
    protected void updateGame(float secondsElapsed) {

        player.update(secondsElapsed);

        if (!player.isInBounds(mCanvasWidth, mCanvasHeight)) {
            //setState(GameThread.STATE_LOSE);
        }

        for (Enemy e : level.getEnemies()) {
            if (isColliding(e) && e.isVisible()) {
                e.setVisible(false);
                updateScore(1);
                player.increaseScale(e.getSize() * 0.2);
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
