package com.mygdx.game.item.armor;

import com.mygdx.game.Main;

public class Boots extends ItemArmor{
    public Boots(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        armor = 6;
        durability = 20;
        image = loadImages("IronArmorBoot");
        imgSizeW = 50;
        imgSizeH = 50;
        itemName = "Boots";
    }
}
