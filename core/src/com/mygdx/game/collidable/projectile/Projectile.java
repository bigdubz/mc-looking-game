package com.mygdx.game.collidable.projectile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;

public abstract class Projectile extends Collidable {


  Collidable projectileOwner;
  protected Projectile(Main m) {
    super(m, "");


  }

  public void draw(Batch batch, float parentAlpha) {
    batch.draw(image, getX(), getY());
  }

  @Override
  public void act(float delta) {}

  public Collidable getOwner() {
    return this.projectileOwner;
  }
}
