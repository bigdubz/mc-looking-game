package com.mygdx.game.state;

import com.mygdx.game.Main;

import java.awt.*;
import java.util.Objects;

public class DayNightCycle {
    Main game;
    public int currentLightLevel; // alpha
    public String state = "day";
    long dayTimeStart;
    int dayDuration; // ms
    int dayLightLevel;
    long nightTimeStart;
    int nightDuration; // ms
    int nightLightLevel;

    public DayNightCycle(Main game, int dayDur, int dayLight, int nightDur, int nightLight){
        this.game = game;
        dayDuration = dayDur*1000;
        dayLightLevel = dayLight;
        currentLightLevel = dayLight;
        nightDuration = nightDur*1000;
        nightLightLevel = nightLight;
        dayTimeStart = System.currentTimeMillis();
    }

    private void changeState() {
        if (Objects.equals(state, "day")
                && System.currentTimeMillis() - dayTimeStart >= dayDuration){
            state = "night";
            nightTimeStart = System.currentTimeMillis();
        }
        else if (Objects.equals(state, "night")
                && System.currentTimeMillis() - nightTimeStart >= nightDuration){
            state = "day";
            dayTimeStart = System.currentTimeMillis();
        }
    }
    public void update(){
        changeState();
        changeDim();
    }
    void changeDim(){
        if (Objects.equals(state, "day")){
            if (currentLightLevel > dayLightLevel) currentLightLevel--;
            if (game.player.vision < game.player.dayVision) game.player.vision += 0.05;
        }
        else if (Objects.equals(state, "night")){
            if (currentLightLevel < nightLightLevel) currentLightLevel++;
            if (game.player.vision > game.player.nightVision) game.player.vision -= 0.05;
        }
    }
    public void drawDim(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, currentLightLevel));
        g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
    }
}
