package com.mygdx.game.item.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;
import com.mygdx.game.item.BaseItem;

public class Inventory {

    BasePlayer owner;
    Main main;
    Array<BaseItem> items;
    Rectangle rectangle;
    int currentIndex = 0;

    public Inventory(Main m, BasePlayer owner) {
        this.main = m;
        this.owner = owner;
        items = new Array<>();
        rectangle = new Rectangle(50, Gdx.graphics.getHeight() - 50, 500, 500);
    }

    public void selectNextItem() {
        currentIndex = (currentIndex + 1) % items.size;
        owner.setSelectedItem(items.get(currentIndex));
    }

    public void selectPrevItem() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = items.size - 1;
        }
        owner.setSelectedItem(items.get(currentIndex));
    }

    public void addItem(BaseItem item) {
        this.items.add(item);
    }

    public Array<BaseItem> getItems() {
        return items;
    }

    public BaseItem getItem(int index) {
        return items.get(index);
    }

    public void clearInventory() {
        items.clear();
    }
}
