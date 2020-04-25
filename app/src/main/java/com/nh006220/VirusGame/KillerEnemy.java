package com.nh006220.VirusGame;

import android.graphics.BitmapFactory;

public class KillerEnemy extends Enemy {
    public KillerEnemy(GameView gameView, float x, float y, int scale) {
        super(gameView, x, y, scale);
        setImage(BitmapFactory.decodeResource(gameView.getContext().getResources(), R.drawable.smiley_ball));
    }


    @Override
    public boolean doesDamage() {
        return true;
    }
}
