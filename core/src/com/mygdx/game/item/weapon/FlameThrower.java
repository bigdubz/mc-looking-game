package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import game.GamePanel;
import game.output.SoundManager;
import player.Player;
import projectile.Flame;
import projectile.Projectile;

import java.util.LinkedList;

public class FlameThrower extends ItemWeapon{

    public FlameThrower(Main game){
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        minDamage = 2;
        maxDamage = 3;
        fireRate = 75;
        projS = 15;
        spread = 15;
        shotsFired = 4;
        magSize = 150;
        currentAmmo = magSize;
        reloadTime = 4000;
        image = loadImages("FlameThrower");
        imgSizeW = image.getWidth();
        imgSizeH = image.getHeight();
        itemName = "Flame Thrower";
        shoot = new SoundManager("");
        reload = new SoundManager("glockReload");
    }

    @Override
    public void shootProjectile(LinkedList<Projectile> projList,
                                GamePanel game,
                                Player owner,
                                double x,
                                double y,
                                double angle,
                                double angleTau,
                                int damage) {

        projList.add(new Flame(game,
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
