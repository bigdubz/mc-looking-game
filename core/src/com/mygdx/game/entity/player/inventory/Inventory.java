package com.mygdx.game.entity.player.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.item.BaseItem;

public class Inventory {

  Main main;
  Array<BaseItem> items;
  Rectangle rectangle;

  public Inventory(Main m) {
    this.main = m;
    items = new Array<>();
    rectangle = new Rectangle(50, Gdx.graphics.getHeight() - 50, 500, 500);
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
