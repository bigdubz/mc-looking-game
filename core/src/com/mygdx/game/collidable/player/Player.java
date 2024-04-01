package com.mygdx.game.collidable.player;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;
import com.mygdx.game.collidable.projectile.Bullet;

public abstract class Player extends Collidable {

  public Player(Main m, String imagePath) {
    super(m, imagePath);
  }

  protected void shootProjectile() {
    new Bullet(
        main,
        this,
        (float)
            Math.atan2(
                Gdx.graphics.getHeight() * 0.5f - Gdx.input.getY(),
                Gdx.graphics.getWidth() * -0.5f + Gdx.input.getX()),
        500,
        getX(),
        getY());
  }
}
