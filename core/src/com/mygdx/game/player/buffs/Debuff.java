package com.mygdx.game.player.buffs;

import game.GamePanel;
import player.Player;

public class Debuff extends Buffs {

    int damage;
    double speedSlow;
    int armorReduction;
    double startSpeed;
    int startArmor;
    int currentTime = 0;
    int duration;
    String name;
    int intervalTime = 0;
    int interval = 75;

    public Debuff(GamePanel game,
                  Player player,
                  Player starter,
                  int damage,
                  int speedSlow,
                  int armorReduction,
                  int duration,
                  String name) {
        this.game = game;
        this.player = player;
        this.startSpeed = player.moveSpeed;
        this.startArmor = player.armor;
        this.starter = starter;
        this.damage = damage;
        this.speedSlow = speedSlow;
        this.armorReduction = armorReduction;
        this.duration = duration;
        this.name = name;
    }

    public void takeEffect() {
        if (update) {
            if (intervalTime > interval) {
                player.armor -= armorReduction;
                player.moveSpeed -= speedSlow;
                player.takeDamage(player.armor + damage); // ignore armor
                countKill(player, starter);
                intervalTime = 0;
            }
            intervalTime++;
        }
        checkEnd();
    }

    void checkEnd() {
        currentTime++;
        if (currentTime >= duration) {
            update = false;
            endEffect();
        }
    }

    void resetStats() {
        player.moveSpeed = startSpeed;
        player.armor = startArmor;
    }

    void countKill(Player player, Player starter) {
        if (player.dead()) {
            starter.killCount += 1;
            starter.gainXP();
        }
    }
}
