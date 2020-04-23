package com.nh006220.VirusGame;

public class Map {
    int width, height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int area(){
        return width*height;
    }
}
