package com.mygdx.game.item.armor;


import com.mygdx.game.Main;

public class Headgear extends ItemArmor{
    public Headgear(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        armor = 7;
        durability = 30;
        image = loadImages("IronArmorHead");
        imgSizeW = 43;
        imgSizeH = 50;
        itemName = "Headgear";
    }
}
