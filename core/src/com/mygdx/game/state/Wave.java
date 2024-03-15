package com.mygdx.game.state;

import java.util.LinkedList;


public class Wave {
    int waveNumber;
    public int npcCount;
    int npcLevel;
    public LinkedList<?> allowedWeapons;
    public boolean armorsAllowed;
    long startTime;
    long elapsedTime;

    Wave(int waveNumber, LinkedList<?> allowedWeapons, boolean armorsAllowed){
        this.waveNumber = waveNumber;
        npcCount = 3 + waveNumber;
        npcLevel = waveNumber;
        this.allowedWeapons = allowedWeapons;
        this.armorsAllowed = armorsAllowed;
    }
}
