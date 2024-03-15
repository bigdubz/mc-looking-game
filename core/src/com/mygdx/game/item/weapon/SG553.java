package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import game.GamePanel;
import game.output.SoundManager;
import player.Player;
import projectile.Bullet;
import projectile.Projectile;

import java.util.LinkedList;

public class SG553 extends ItemWeapon{

    public SG553(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 25;
        maxDamage = 30;
        fireRate = 120;
        projS = 35;
        spread = 3;
        shotsFired = 1;
        magSize = 30;
        currentAmmo = magSize;
        reloadTime = 2100;
        image = loadImages("SG553");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "SG553";
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
        projList.add(new Bullet(game,
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
