package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Main;

public abstract class BaseItem extends Actor {

  protected Main main;
  public Sprite sprite;
  String itemName;

  public BaseItem(Main m, String imagePath, String itemName) {
    this.main = m;
    this.sprite = new Sprite(this.main.assets.get(imagePath, Texture.class));
    this.itemName = itemName;
    m.gameScreen.getStage().addActor(this);
  }
}
