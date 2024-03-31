package com.mygdx.game.collidable.player;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;
import com.mygdx.game.collidable.projectile.Projectile;

public abstract class Player extends Collidable {
  Array<Projectile> projectiles;

  public Player(Main m, String imagePath) {
    super(m, imagePath);
    projectiles = new Array<>();
  }
}
