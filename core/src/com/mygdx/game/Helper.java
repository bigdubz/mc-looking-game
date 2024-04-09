package com.mygdx.game;

import java.util.Random;

public class Helper {

    public static final float INVERSE_SQRT_2 = 1 / (float) Math.sqrt(2);
    private static final Random rand = new Random();

    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min + 1)) + min;
    }

    public static float getAngle(
        float originX,
        float originY,
        float x,
        float y
    ) {
        return (float) Math.toDegrees(Math.atan2(y - originY, x - originX));
    }
}
