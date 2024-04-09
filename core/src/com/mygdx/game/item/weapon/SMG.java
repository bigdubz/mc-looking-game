package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public class SMG extends BaseWeapon {

    public SMG(Main m, BasePlayer holder) {
        super(
            m,
            "Items/Submachine.png",
            holder,
            10,
            15,
            90,
            25 * 25,
            7,
            1,
            30,
            30,
            2275,
            "SMG"
        );
    }
}
