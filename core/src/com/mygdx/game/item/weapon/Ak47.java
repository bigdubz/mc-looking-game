package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
//import game.output.SoundManager;
import com.mygdx.game.player.Player;
import com.mygdx.game.projectile.Bullet;
import com.mygdx.game.projectile.Projectile;

import java.util.LinkedList;

public class Ak47 extends ItemWeapon{

    public Ak47(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 10;
        maxDamage = 25;
        fireRate = 100;
        projS = 30;
        spread = 4;
        shotsFired = 1;
        magSize = 30;
        currentAmmo = magSize;
        reloadTime = 1750;
        image = loadImages("AK47");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "AK47";
//        shoot = new SoundManager("ak47Shoot");
//        reload = new SoundManager("ak47Reload");
    }
    public void shootProjectile(LinkedList<Projectile> projList,
                                Main game,
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
