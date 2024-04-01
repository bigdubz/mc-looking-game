package com.mygdx.game.collidable.projectile;

import com.badlogic.gdx.math.Rectangle;
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

  @Override
  protected void checkCollisionAndMove(float dx, float dy) {
    for (Rectangle rect : main.solidBlocks) {
      if (Math.abs(rect.getY() - getY()) <= rect.getHeight() + main.BLOCK_SIZE
          && Math.abs(rect.getX() - getX()) <= rect.getWidth() + main.BLOCK_SIZE) {
        if (collideX(dx, rect) || collideY(dy, rect)) {
          this.collided = true;
          break;
        }
      }
    }
    setX(getX() + dx);
    rectangle.setX(getX());
    sprite.setX(getX());
    setY(getY() + dy);
    rectangle.setY(getY());
    sprite.setY(getY());
  }
}
