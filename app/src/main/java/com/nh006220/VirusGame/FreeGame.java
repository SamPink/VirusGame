package com.nh006220.VirusGame;

import java.util.List;
import java.util.Random;

/**
 * Game mode where either type of enemy is randomly spawned
 * player can keep going for as long as possible
 * when number of enemy falls below threshold (25) spawn more
 */
public class FreeGame extends GameState {
    public FreeGame(GameView gameView, Map map) {
        super(gameView, map);
    }

    @Override
    public void update(float secondsElapsed) {
        if (getRemainingEnemies() < 25) {
            //make new enemy's when remaining falls below value
            makeEnemies(25);
            makeKillerEnemys(5);
        }
    }

    /**
     * add n enemies to enemy array at random position
     * @param n count of enemies to add
     */
    private void makeKillerEnemys(int n) {
        List<Enemy> enemies = getEnemies();

        for (int i = 0; i < n; i++) {
            Random r = new Random();
            //spawn enemies at random position on the map
            enemies.add(new KillerEnemy(getGameView(), r.nextInt((getMap().width - (-getMap().width)) + 1) + (-getMap().width),
                    r.nextInt((getMap().height - (-getMap().height)) + 1) + (-getMap().height),
                    100));
        }

        setEnemies(enemies);
    }

    /**
     * on collision action determines if emmey is removed or if player dies
     * @param e enemy collied with
     * @return true if does damage
     */
    @Override
    public boolean onCollision(Enemy e) {
        if (!e.doesDamage()) {
            e.setVisible(false);
            return false;
        } else {
            //do damage
            return true;
        }
    }

    /**
     * always 0 because no levels in this game mode
     * @return always 0
     */
    @Override
    public int getLevelNumber() {
        return 0;
    }
}
