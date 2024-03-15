package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import game.GamePanel;
import game.output.SoundManager;
import player.Player;
import projectile.Bullet;
import projectile.Projectile;

import java.util.LinkedList;

public class Submachine extends ItemWeapon{

    public Submachine(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 10;
        maxDamage = 15;
        fireRate = 90;
        projS = 25;
        spread = 7;
        shotsFired = 1;
        magSize = 30;
        currentAmmo = magSize;
        reloadTime = 2275;
        image = loadImages("Submachine");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "Submachine Gun";
        shoot = new SoundManager("subShoot");
        reload = new SoundManager("subReload");
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
