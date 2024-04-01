package com.mygdx.game.collidable.projectile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;

public abstract class Projectile extends Collidable {

  Collidable projectileOwner;
  float rotation;
  float speed;
  float speedX;
  float speedY;
  boolean collided = false;

  protected Projectile(
      Main m, Collidable owner, String imagePath, float rotation, float speed, float x, float y) {
    super(m, imagePath);
    this.speed = speed;
    this.projectileOwner = owner;
    this.speedX = speed * (float) Math.cos(rotation);
    this.speedY = speed * (float) Math.sin(rotation);
    setX(x);
    setY(y);
    image.setRotation(rotation);
    this.setRotation(rotation);
  }

  public Collidable getOwner() {
    return this.projectileOwner;
  }

  public float getRotation() {
    return this.rotation;
  }

  public float getSpeed() {
    return this.speed;
  }
}
