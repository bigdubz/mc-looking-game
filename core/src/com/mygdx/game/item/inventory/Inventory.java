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
        if (items.isEmpty()) {
            owner.setSelectedItem(null);
            return;
        }
        items.get(currentIndex).itemDeselected();
        currentIndex = (currentIndex + 1) % items.size;
        owner.setSelectedItem(items.get(currentIndex));
        items.get(currentIndex).itemSelected();
    }

    public void selectPrevItem() {
        if (items.isEmpty()) {
            owner.setSelectedItem(null);
            return;
        }
        items.get(currentIndex).itemDeselected();
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = items.size - 1;
        }
        owner.setSelectedItem(items.get(currentIndex));
        items.get(currentIndex).itemSelected();
    }

    public void addItem(BaseItem item) {
        this.items.add(item);
    }

    public BaseItem getItem(int index) {
        return items.get(index);
    }

    public void dropItem() {
        if (items.isEmpty()) {
            return;
        }
        items.get(currentIndex).itemDropped();
        items.removeIndex(currentIndex);
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = items.size - 1;
        }
        selectPrevItem();
    }

    public void clearInventory() {
        items.clear();
    }

    public Array<BaseItem> getItems() {
        return this.items;
    }
}
