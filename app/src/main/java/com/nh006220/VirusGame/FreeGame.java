package com.nh006220.VirusGame;

import java.util.List;
import java.util.Random;

public class FreeGame extends GameState {
    public FreeGame(GameView gameView, Map map) {
        super(gameView, map);
    }

    @Override
    public void update(float secondsElapsed) {
        if(getRemainingEnemies() < 25){
            makeEnemies(25);
            makeKillerEnemys(5);
        }
    }

    private void makeKillerEnemys(int n) {
        List<Enemy> enemies = getEnemies();

        for (int i = 0; i < n; i++) {
            Random r = new Random();
            enemies.add(new KillerEnemy(getGameView(), r.nextInt((getMap().width - (-getMap().width)) + 1) + (-getMap().width),
                    r.nextInt((getMap().height - (-getMap().height)) + 1) + (-getMap().height),
                    100));
        }

        setEnemies(enemies);
    }

    @Override
    public boolean onCollision(Enemy e) {
        if(!e.doesDamage()){
            e.setVisible(false);
            return false;
        }else{
            //do damage
            return true;
        }
    }

    @Override
    public int getLevelNumber() {
        return 0;
    }
}
