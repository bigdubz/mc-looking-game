package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public class Pistol extends BaseWeapon {

    public Pistol(Main m, BasePlayer holder) {
        super(
            m,
            "Items/Pistol.png",
            holder,
            5,
            10,
            250,
            25 * 25,
            3,
            1,
            9,
            9,
            1000,
            "Glock"
        );
    }
}
