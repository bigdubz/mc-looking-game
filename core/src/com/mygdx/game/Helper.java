package com.mygdx.game;

import java.util.Random;

public class Helper {

    static Random rand = new Random();

    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min + 1)) + min;
    }
}
