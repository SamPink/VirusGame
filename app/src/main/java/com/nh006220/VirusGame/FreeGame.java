package com.nh006220.VirusGame;

import java.util.List;
import java.util.Random;

public class FreeGame extends GameState {
    public FreeGame(GameView gameView, Map map) {
        super(gameView, map);
    }

    @Override
    public void update(float secondsElapsed) {
        if(getRemainingEnemies() < 10){
            makeEnemies(10);
        }
    }

    @Override
    public int getLevelNumber() {
        return 0;
    }
}
