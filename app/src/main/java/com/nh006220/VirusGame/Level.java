package com.nh006220.VirusGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    private final int levelNumber;
    private final int speed;
    private final GameView gameView;
    private List<Enemy> enemies;
    private int enemiesCount;
    private Map map;


    public Level(int number, int enemiesCount, int enemiesSpeed, GameView gameView, Map map) {
        this.levelNumber = number;
        this.enemiesCount = enemiesCount;
        this.speed = enemiesSpeed;
        this.gameView = gameView;
        this.enemies = new ArrayList<>();
        this.map = map;

        for (int i = 0; i < this.enemiesCount; i++) {
            Random r = new Random();
            enemies.add(new Enemy(gameView,
                    r.nextInt((map.width - (-map.width)) + 1) + (-map.width),
                    r.nextInt((map.height - (-map.height)) + 1) + (-map.height),
                    100));
        }
    }

    public int getRemainingEnemies(){
        int en = 0;
        for ( Enemy e: enemies) {
            if(e.isVisible()){
                en++;
            }
        }
        return en;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getEnemiesCount() {
        return enemiesCount;
    }

    public void setEnemiesCount(int enemiesCount) {
        this.enemiesCount = enemiesCount;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
