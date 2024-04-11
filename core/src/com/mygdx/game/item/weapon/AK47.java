package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public class AK47 extends BaseWeapon {

    public AK47(Main m, BasePlayer holder) {
        super(
            m,
            "Items/AK47.png",
            holder,
            10,
            25,
            100,
            30 * 25,
            4,
            1,
            30,
            1750,
            "AK47"
        );
    }
}
