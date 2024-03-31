package com.mygdx.game.collidable.projectile;

import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;

public class Bullet extends Projectile {

  protected Bullet(Main m, Collidable owner, float rotation) {
    super(m, owner, "Items/bullet.png", rotation);
  }
}
