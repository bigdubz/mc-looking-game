package com.mygdx.game.collidable.projectile;

import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;

public class Bullet extends Projectile {

  public Bullet(Main m, Collidable owner, float rotation, float speed, float x, float y) {
    super(m, owner, "Items/bullet.png", rotation, speed, x, y);
  }

  @Override
  public void act(float delta) {
    float dx = speedX * delta;
    float dy = speedY * delta;
    if (!collided) {
      checkCollisionAndMove(dx, dy);
    } else {
      this.remove();
    }
  }
}
