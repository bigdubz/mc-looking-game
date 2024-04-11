package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public class SniperRifle extends BaseWeapon {

    public SniperRifle(Main m, BasePlayer holder) {
        super(
            m,
            "Items/AWP.png",
            holder,
            75,
            120,
            1450,
            40 * 25,
            0,
            1,
            5,
            3000,
            "Sniper Rifle"
        );
    }
}
