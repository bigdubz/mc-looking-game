package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Main;

public abstract class BaseItem extends Actor {

  Main main;
  public Texture image;
  String itemName;

  public BaseItem(Main m, String imagePath, String itemName) {
    this.main = m;
    this.image = m.assets.get(imagePath);
    this.itemName = itemName;
  }
}
