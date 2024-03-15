package com.mygdx.game.item.armor;

import com.mygdx.game.item.Item;

public abstract class ItemArmor extends Item {
    public int armor; // subtract armor from each damaging hit
    public double durability; // every hit decreases by durability rate value
    boolean equipped = false;
}
