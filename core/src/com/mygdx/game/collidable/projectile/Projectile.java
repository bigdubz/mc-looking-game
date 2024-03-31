package com.mygdx.game.collidable.projectile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;

public abstract class Projectile extends Collidable {

  Collidable projectileOwner;
  float rotation;

  protected Projectile(Main m, Collidable owner, String imagePath, float rotation) {
    super(m, imagePath);
    this.projectileOwner = owner;
    image.setRotation(rotation);
  }

  public void draw(Batch batch, float parentAlpha) {
    batch.draw(image, getX(), getY());
  }

  @Override
  public void act(float delta) {}

  public Collidable getOwner() {
    return this.projectileOwner;
  }

  public float getRotation() {
    return this.rotation;
  }
}
