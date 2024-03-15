package com.mygdx.game.item.armor;


import com.mygdx.game.Main;

public class Gloves extends ItemArmor{

    public Gloves(Main game){
        this.game = game;
        point = this.game.getRandomEmptyPoint();
        X = point[0];
        Y = point[1];
        armor = 5;
        durability = 20;
        image = loadImages("IronArmorGlove");
        imgSizeW = 50;
        imgSizeH = 50;
        itemName = "Gloves";
    }
}
