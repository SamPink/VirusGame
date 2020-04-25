package com.nh006220.VirusGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GameState {
    private int speed;
    private final GameView gameView;
    private List<Enemy> enemies;
    private int enemiesCount;
    private Map map;

    public GameState(GameView gameView, Map map) {
        this.gameView = gameView;
        this.map = map;
        this.enemies = new ArrayList<>();
    }
    
    
    public int getRemainingEnemies(){
        int r = 0;
        for (Enemy e: getEnemies()) {
            if(e.isVisible()) r++;
        }
        return r;
    }

    public void makeEnemies(int n){
        List<Enemy> enemies = getEnemies();

        for (int i = 0; i < n; i++) {
            Random r = new Random();
            enemies.add(new Enemy(getGameView(), r.nextInt((getMap().width - (-getMap().width)) + 1) + (-getMap().width),
                    r.nextInt((getMap().height - (-getMap().height)) + 1) + (-getMap().height),
                    100));
        }

        setEnemies(enemies);
    }
    

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GameView getGameView() {
        return gameView;
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public abstract int getLevelNumber();

    public abstract void update(float secondsElapsed);
}
