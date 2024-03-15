package com.mygdx.game.player.buffs;

import com.mygdx.game.Main;
import com.mygdx.game.player.Player;


public abstract class Buffs {

    Main game;
    Player player;
    Player starter;
    public boolean update = true;


    public void startEffect() {
        player.buffs.add(this);
    }
    public abstract void takeEffect();
    void endEffect() {
        resetStats();
    }
    abstract void resetStats();
}
