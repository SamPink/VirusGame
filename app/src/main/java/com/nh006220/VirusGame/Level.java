package com.nh006220.VirusGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level extends GameState{
    private int levelNumber;


    public Level(GameView gameView, Map map, int levelNumber, int enemyCount) {
        super(gameView, map);

        this.levelNumber = levelNumber;

        setEnemiesCount(enemyCount);

        makeEnemies(enemyCount);
    }

    @Override
    public boolean onCollision(Enemy e) {
        e.setVisible(false);
        //always return false in this game mode as enemies don't do damage
        return false;
    }

    @Override
    public void update(float secondsElapsed) {

    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
