package com.mygdx.game.collidable.projectile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;

public class Bullet extends Projectile {

  public Bullet(Main m, Collidable owner, float rotation, float speed, float x, float y) {
    super(m, owner, "Items/bullet.png", rotation, speed, x, y);
  }

  public void draw(Batch batch, float parentAlpha) {
    batch.draw(image, getX(), getY());
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
    boolean collidedX = false;
    boolean collidedY = false;
    for (Rectangle rect : main.solidBlocks) {
      if (Math.abs(getRow(rect) - getRow()) <= rect.getHeight() * main.INVERSE_BLOCK_SIZE
          && Math.abs(getCol(rect) - getCol()) <= rect.getWidth() * main.INVERSE_BLOCK_SIZE) {
        if (collideX(dx, rect)) {
          collidedX = true;
          break;
        }
        if (collideY(dy, rect)) {
          collidedY = true;
          break;
        }
      }
    }
    collided = collidedX || collidedY;
    if (!collidedX) {
      setX(getX() + dx);
      rectangle.setX(getX());
    }
    if (!collidedY) {
      setY(getY() + dy);
      rectangle.setY(getY());
    }
  }
}
