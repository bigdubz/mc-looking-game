package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import game.GamePanel;
import game.output.SoundManager;
import player.Player;
import projectile.Bullet;
import projectile.Projectile;

import java.util.LinkedList;

public class Shotgun extends ItemWeapon{

    public Shotgun(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 25;
        maxDamage = 50;
        fireRate = 500;
        projS = 30;
        spread = 10;
        shotsFired = 4;
        magSize = 2;
        currentAmmo = magSize;
        reloadTime = 1700;
        image = loadImages("Shotgun");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "Shotgun";
        shoot = new SoundManager("shotgunShoot");
        reload = new SoundManager("shotgunReload");
    }
    public void shootProjectile(LinkedList<Projectile> projList,
                                GamePanel game,
                                Player owner,
                                double x,
                                double y,
                                double angle,
                                double angleTau,
                                int damage){
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
