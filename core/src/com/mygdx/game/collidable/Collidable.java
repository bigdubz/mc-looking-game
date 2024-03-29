package com.mygdx.game.collidable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.Main;

public abstract class Collidable extends Actor {

  protected Main main;
  protected Sprite image;
  protected Rectangle tempRect;
  public Rectangle rectangle;
  public float halfWidth;
  public float halfHeight;

  protected Collidable(Main m) {
    main = m;
    setTouchable(Touchable.enabled);
  }

  protected void checkCollisionAndMove(float dx, float dy) {
    boolean collidedX = false;
    boolean collidedY = false;
    //    int count = 0;
    for (Rectangle rect : main.solidBlocks) {
      int rectCol = (int) (rect.getX() * main.INVERSE_BLOCK_SIZE);
      int rectRow = (int) (rect.getY() * main.INVERSE_BLOCK_SIZE);
      if (Math.abs(rectRow - getRow()) > rect.getHeight() * main.INVERSE_BLOCK_SIZE
          || Math.abs(rectCol - getCol()) > rect.getWidth() * main.INVERSE_BLOCK_SIZE) continue;
      //      count++;
      if (!collidedX && collideX(dx, rect)) collidedX = true;
      if (!collidedY && collideY(dy, rect)) collidedY = true;
    }
    //    Gdx.app.log("Objects being checked", "" + count);
    if (!collidedX) {
      setX(getX() + dx);
      rectangle.setX(getX());
    }
    if (!collidedY) {
      setY(getY() + dy);
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

  public int getCol() {
    return (int) (getX() * main.INVERSE_BLOCK_SIZE);
  }

  public int getRow() {
    return (int) (getY() * main.INVERSE_BLOCK_SIZE);
  }

  public float getHalfWidth() {
    return this.halfWidth;
  }

  public float getHalfHeight() {
    return this.halfHeight;
  }

  public Rectangle getRectangle() {
    return this.rectangle;
  }

  public void dispose() {
    image.getTexture().dispose();
  }
}
