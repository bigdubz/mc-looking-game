package com.mygdx.game.collidable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.Main;

public abstract class Collidable extends Actor {

  protected Main main;
  protected Sprite image;
  protected Rectangle tempRect;
  public Rectangle rectangle;

  protected Collidable(Main m) {
    main = m;
    setTouchable(Touchable.enabled);
  }

  protected void checkCollisionAndMove(float horz, float vert) {
    boolean collidedX = false;
    boolean collidedY = false;
    for (Rectangle rect : main.solidBlocks) {
      if (!collidedX && collideX(horz, rect)) collidedX = true;
      if (!collidedY && collideY(vert, rect)) collidedY = true;
    }
    if (!collidedX) {
      setX(getX() + horz);
      rectangle.setX(getX());
    }
    if (!collidedY) {
      setY(getY() + vert);
      rectangle.setY(getY());
    }
  }

  boolean collideX(float dx, Rectangle block) {
    tempRect.setX(getX() + dx);
    boolean collided = rectangle.overlaps(block);
    tempRect.setX(getX());
    return collided;
  }

  boolean collideY(float dy, Rectangle block) {
    tempRect.setY(getY() + dy);
    boolean collided = rectangle.overlaps(block);
    tempRect.setY(getY());
    return collided;
  }

  public void dispose() {
    image.getTexture().dispose();
  }
}
