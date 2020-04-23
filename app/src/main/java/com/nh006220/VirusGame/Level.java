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


    public Level(int number, int enemiesCount, int enemiesSpeed, GameView gameView) {
        this.levelNumber = number;
        this.enemiesCount = enemiesCount;
        this.speed = enemiesSpeed;
        this.gameView = gameView;
        this.enemies = new ArrayList<>();


        for (int i = 0; i < this.enemiesCount; i++) {
            Random r = new Random();
            enemies.add(new Enemy(gameView,
                    r.nextInt((2000 - (-2000)) + 1) + (-2000),
                    r.nextInt((2000 - (-2000)) + 1) + (-2000),
                    100));
        }
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
}
