package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import game.*;
import game.output.SoundManager;
import player.Player;
import projectile.Projectile;
import projectile.Rocket;

import java.util.LinkedList;

public class RocketLauncher extends ItemWeapon{
    public RocketLauncher(Main game){
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 85;
        maxDamage = 150;
        fireRate = 1000;
        projS = 25;
        spread = 1;
        shotsFired = 1;
        magSize = 1;
        currentAmmo = magSize;
        reloadTime = 4500;  // ms
        image = loadImages("RocketLauncher");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "Rocket Launcher";
        shoot = new SoundManager("SSGShoot");
        reload = new SoundManager("SSGReload");
    }
    public void shootProjectile(LinkedList<Projectile> projList,
                                GamePanel game,
                                Player owner,
                                double x,
                                double y,
                                double angle,
                                double angleTau,
                                int damage) {

        // give respective projectile here
        projList.add(new Rocket(game,
                owner,
                x,
                y,
                angle,
                angleTau,
                damage,
                projS,
                spread));
    }
}
