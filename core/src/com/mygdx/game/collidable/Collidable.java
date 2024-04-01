package com.mygdx.game.collidable;

import com.badlogic.gdx.graphics.Texture;
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

  protected Collidable(Main m, String imagePath) {
    main = m;
    image = new Sprite(main.assets.get(imagePath, Texture.class));
    rectangle = image.getBoundingRectangle();
    tempRect = rectangle;
    setTouchable(Touchable.enabled);
    main.gameScreen.stage.addActor(this);
  }

  protected void checkCollisionAndMove(float dx, float dy) {
    boolean collidedX = false;
    boolean collidedY = false;
    //    int count = 0;
    for (Rectangle rect : main.solidBlocks) {
      if (Math.abs(rect.getY() - getY()) <= rect.getHeight() + main.BLOCK_SIZE
          && Math.abs(rect.getX() - getX()) <= rect.getWidth() + main.BLOCK_SIZE) {
        //        count++;
        if (!collidedX && collideX(dx, rect)) {
          collidedX = true;
        }
        if (!collidedY && collideY(dy, rect)) {
          collidedY = true;
        }
      }
      if (collidedX && collidedY) {
        break;
      }
    }
    //    Gdx.app.log("count", "" + count);
    if (!collidedX) {
      setX(getX() + dx);
      rectangle.setX(getX());
    }
    if (!collidedY) {
      setY(getY() + dy);
      rectangle.setY(getY());
    }
  }

  protected boolean collideX(float dx, Rectangle block) {
    tempRect.setX(getX() + dx);
    boolean collided = rectangle.overlaps(block);
    tempRect.setX(getX());
    return collided;
  }

  protected boolean collideY(float dy, Rectangle block) {
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

  public int getCol(Rectangle rect) {
    return (int) (rect.getX() * main.INVERSE_BLOCK_SIZE);
  }

  public int getRow(Rectangle rect) {
    return (int) (rect.getY() * main.INVERSE_BLOCK_SIZE);
  }

  public int getCol(Collidable object) {
    return (int) (object.getX() * main.INVERSE_BLOCK_SIZE);
  }

  public int getRow(Collidable object) {
    return (int) (object.getY() * main.INVERSE_BLOCK_SIZE);
  }

  public Rectangle getRectangle() {
    return this.rectangle;
  }

  public float getHalfWidth() {
    return this.halfWidth;
  }

  public float getHalfHeight() {
    return this.halfHeight;
  }

  public void dispose() {
    image.getTexture().dispose();
  }
}
