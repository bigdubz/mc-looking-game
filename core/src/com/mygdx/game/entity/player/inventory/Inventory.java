package com.mygdx.game.entity.player.inventory;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.item.BaseItem;

public class Inventory {
  Main main;
  Array<BaseItem> items;

  public Inventory(Main m) {
    this.main = m;
    items = new Array<>();
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
