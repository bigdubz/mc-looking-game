package com.mygdx.game.collidable.player;

import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;

public abstract class Player extends Collidable {
  public Player(Main m, String image) {
    super(m, image);
  }
}
