package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public class AssaultRifle extends BaseWeapon {

    public AssaultRifle(Main m, BasePlayer holder) {
        super(
            m,
            "Items/SG553.png",
            holder,
            25,
            30,
            120,
            35 * 25,
            3,
            1,
            30,
            2100,
            "Assault Rifle"
        );
    }
}
