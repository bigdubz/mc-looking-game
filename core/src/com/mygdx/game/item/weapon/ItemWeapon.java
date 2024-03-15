package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.item.Item;
import com.mygdx.game.player.Player;
import com.mygdx.game.projectile.Projectile;

import java.util.LinkedList;

public abstract class ItemWeapon extends Item {

    public int minDamage;
    public int maxDamage;
    public int fireRate;
    public int projS;
    public int spread;
    public int shotsFired;
    public int magSize;
    public int currentAmmo;
    public int reloadTime;
    public long reloadStart;
//    public SoundManager shoot;
//    public SoundManager reload;

    public abstract void shootProjectile(LinkedList<Projectile> projList,
                                         Main game,
                                         Player owner,
                                         double x,
                                         double y,
                                         double angle,
                                         double angleTau,
                                         int damage);
}
