package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public class SMG extends BaseWeapon {

    public SMG(Main m, BasePlayer holder) {
        super(
            m,
            "Items/Submachine.png",
            holder,
            7,
            10,
            90,
            25 * 25,
            7,
            1,
            30,
            2275,
            "SMG"
        );
    }
}
