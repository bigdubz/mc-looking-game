package com.mygdx.game.collidable;

import com.badlogic.gdx.Gdx;
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
  private final Rectangle tempRectMapObject;
  private final Rectangle tempScaledRectMapObject;
  public Rectangle rectangle;

  protected Collidable(Main m) {
    main = m;
    setTouchable(Touchable.enabled);
    tempScaledRectMapObject = new Rectangle();
    tempRectMapObject = new Rectangle();
  }

  protected void checkCollisionAndMove(float horz, float vert) {
    main.gameScreen.tree.clear();
    for (RectangleMapObject rectMapObject : main.solidBlocks.getByType(RectangleMapObject.class)) {
      tempRectMapObject.set(rectMapObject.getRectangle());
      tempScaledRectMapObject.set(
          tempRectMapObject.getX() * main.mapScale,
          tempRectMapObject.getY() * main.mapScale,
          tempRectMapObject.getWidth() * main.mapScale,
          tempRectMapObject.getHeight() * main.mapScale);
      main.gameScreen.tree.insert(tempScaledRectMapObject);
    }

    main.gameScreen.nearbyBlocks.clear();
    main.gameScreen.tree.retrieve(main.gameScreen.nearbyBlocks, rectangle);

    boolean collidedX = false;
    boolean collidedY = false;
    for (Rectangle r : main.gameScreen.nearbyBlocks) {
      if (!collidedX && collideX(horz, r)) collidedX = true;
      if (!collidedY && collideY(vert, r)) collidedY = true;
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
