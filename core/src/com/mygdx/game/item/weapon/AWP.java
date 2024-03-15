package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import game.GamePanel;
import game.output.SoundManager;
import player.Player;
import projectile.Bullet;
import projectile.Projectile;

import java.util.LinkedList;

public class AWP extends ItemWeapon{

    public AWP(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 75;
        maxDamage = 120;
        fireRate = 1450;
        projS = 35;
        spread = 0;
        shotsFired = 1;
        magSize = 5;
        currentAmmo = magSize;
        reloadTime = 3000;
        image = loadImages("AWP");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "AWP";
        shoot = new SoundManager("AWPShoot");
        reload = new SoundManager("AWPReload");
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
