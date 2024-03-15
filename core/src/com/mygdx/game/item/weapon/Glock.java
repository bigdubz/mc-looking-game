package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import game.GamePanel;
import game.output.SoundManager;
import player.Player;
import projectile.Bullet;
import projectile.Projectile;

import java.util.LinkedList;

public class Glock extends ItemWeapon{
    GamePanel game;
    public Glock(Main game){
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 5;
        maxDamage = 15;
        fireRate = 250;
        projS = 25;
        spread = 3;
        shotsFired = 1;
        magSize = 9;
        currentAmmo = magSize;
        reloadTime = 1000;
        image = loadImages("Pistol");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "Glock";
        shoot = new SoundManager("glockShoot");
        reload = new SoundManager("glockReload");
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
