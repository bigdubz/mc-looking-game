package com.mygdx.game.item.armor;


import com.mygdx.game.Main;

public class Chest extends ItemArmor{
    public Chest(Main game) {
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        armor = 10;
        durability = 50;
        image = loadImages("IronArmorChest");
        imgSizeW = 50;
        imgSizeH = 50;
        itemName = "Chest Plate";
    }
}
