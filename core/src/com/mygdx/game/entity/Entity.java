package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;
import com.mygdx.game.entity.projectile.BaseProjectile;

public abstract class Entity extends Actor {

  protected Main main;
  protected Sprite sprite;
  public Rectangle rectangle;
  public float halfWidth;
  public float halfHeight;

  protected Entity(Main m, String imagePath) {
    this.main = m;
    this.sprite = new Sprite(main.assets.get(imagePath, Texture.class));
    this.rectangle = sprite.getBoundingRectangle();
    this.setWidth(rectangle.getWidth());
    this.setHeight(rectangle.getHeight());
    this.halfWidth = getWidth() * 0.5f;
    this.halfHeight = getHeight() * 0.5f;
    this.setTouchable(Touchable.enabled);
    this.main.gameScreen.stage.addActor(this);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    if (!outOfBounds(this)) {
      sprite.draw(batch);
    }
  }

  protected void checkCollisionAndMove(float dx, float dy) {
    boolean collidedX = false;
    boolean collidedY = false;
    for (Rectangle rect : main.solidBlocks) {
      if (!(Math.abs(rect.getY() - getY()) > rect.getHeight() + main.BLOCK_SIZE
          || Math.abs(rect.getX() - getX()) > rect.getWidth() + main.BLOCK_SIZE)) {
        if (!(collidedX || !collideX(dx, rect))) {
          collidedX = true;
          moveOffsetX(rect, dx);
        }
        if (!(collidedY || !collideY(dy, rect))) {
          collidedY = true;
          moveOffsetY(rect, dy);
        }
      }
      if (!(!collidedX || !collidedY)) {
        break;
      }
    }

    // Check collision with game members
    for (Actor actor : main.gameScreen.stage.getActors()) {
      if (!(!(actor instanceof BasePlayer) || actor == this || this instanceof BaseProjectile)) {
        if (collideX(dx, ((Entity) actor).getRectangle())) {
          collidedX = true;
        }
        if (collideY(dy, ((Entity) actor).getRectangle())) {
          collidedY = true;
        }
      }
      if (!(!collidedX || !collidedY)) {
        break;
      }
    }
    if (!collidedX) {
      setXProps(getX() + dx);
    }
    if (!collidedY) {
      setYProps(getY() + dy);
    }
  }

  public void setXProps(float x) {
    setX(x);
    rectangle.setX(getX());
    sprite.setX(getX());
  }

  public void setYProps(float y) {
    setY(y);
    rectangle.setY(getY());
    sprite.setY(getY());
  }

  protected void moveOffsetX(Rectangle block, float dx) {
    if (dx > 0) {
      setXProps(block.getX() - getWidth());
    } else {
      setXProps(block.getX() + block.getWidth());
    }
  }

  protected void moveOffsetY(Rectangle block, float dy) {
    if (dy > 0) {
      setYProps(block.getY() - getHeight());
    } else {
      setYProps(block.getY() + block.getHeight());
    }
  }

  protected boolean collideX(float dx, Rectangle block) {
    rectangle.setX(getX() + dx);
    boolean collided = rectangle.overlaps(block);
    rectangle.setX(getX());
    return collided;
  }

  protected boolean collideY(float dy, Rectangle block) {
    rectangle.setY(getY() + dy);
    boolean collided = rectangle.overlaps(block);
    rectangle.setY(getY());
    return collided;
  }

  public boolean outOfBounds(Entity object) {
    return object.getX() + object.getWidth()
            < main.gameScreen.getStage().getCamera().position.x - Gdx.graphics.getWidth() * 0.5f
        || object.getX()
            > main.gameScreen.getStage().getCamera().position.x + Gdx.graphics.getWidth() * 0.5f
        || object.getY() + object.getHeight()
            < main.gameScreen.getStage().getCamera().position.y - Gdx.graphics.getHeight() * 0.5f
        || object.getY()
            > main.gameScreen.getStage().getCamera().position.y + Gdx.graphics.getHeight() * 0.5f;
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

  public int getCol(Entity object) {
    return (int) (object.getX() * main.INVERSE_BLOCK_SIZE);
  }

  public int getRow(Entity object) {
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

  public float getAngle(float originX, float originY, float x, float y) {
    return (float) Math.toDegrees(Math.atan2(y - originY, x - originX));
  }

  public void dispose() {
    sprite.getTexture().dispose();
  }
}
