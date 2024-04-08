package com.mygdx.game.entity.projectile;

import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;

public class Bullet extends BaseProjectile {

  public Bullet(
    Main m,
    Entity owner,
    float rotation,
    float speed,
    float x,
    float y,
    int damage
  ) {
    super(m, owner, "Items/bullet.png", rotation, speed, x, y, damage);
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
