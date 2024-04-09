package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public class Shotgun extends BaseWeapon {

    public Shotgun(Main m, BasePlayer holder) {
        super(
            m,
            "Items/Shotgun.png",
            holder,
            25,
            50,
            500,
            30 * 25,
            10,
            4,
            2,
            2,
            1700,
            "Shotgun"
        );
    }
}
