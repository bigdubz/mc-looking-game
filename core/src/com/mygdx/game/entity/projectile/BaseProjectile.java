package com.mygdx.game.entity.projectile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.player.BasePlayer;

public abstract class BaseProjectile extends Entity {

  final Entity owner;
  BasePlayer memberHit = null;
  int damage;
  float rotation;
  float speed;
  float speedX;
  float speedY;
  boolean collided = false;

  protected BaseProjectile(
      Main m,
      Entity owner,
      String imagePath,
      float rotation,
      float speed,
      float x,
      float y,
      int damage) {
    super(m, imagePath);
    this.speed = speed;
    this.owner = owner;
    this.speedX = speed * (float) Math.cos(Math.toRadians(rotation));
    this.speedY = speed * (float) Math.sin(Math.toRadians(rotation));
    this.damage = damage;
    this.rotation = rotation;
    this.sprite.setRotation(this.rotation);
    this.setRotation(this.rotation);
    this.setPosition(x, y);
  }

  @Override
  protected void checkCollisionAndMove(float dx, float dy) {
    for (Rectangle rect : main.solidBlocks) {
      // Same as Entity check
      if (!(Math.abs(rect.getY() - getY()) > rect.getHeight() + main.BLOCK_SIZE
          || Math.abs(rect.getX() - getX()) > rect.getWidth() + main.BLOCK_SIZE)) {
        if (collideX(dx, rect)) {
          this.moveOffsetX(rect, dx);
          this.collided = true;
          break;
        }
        if (collideY(dy, rect)) {
          this.moveOffsetY(rect, dy);
          this.collided = true;
          break;
        }
      }
    }
    this.collided = this.collided || checkCollisionWithActors(dx, dy);
    if (!this.collided) {
      setXProps(getX() + dx);
      setYProps(getY() + dy);
    }
  }

  boolean checkCollisionWithActors(float dx, float dy) {
    for (Actor actor : main.gameScreen.stage.getActors()) {
      // If (actor instanceof BasePlayer) and (actor != owner (so that we don't shoot ourselves lol))
      if (!(!(actor instanceof BasePlayer) || actor == owner)) {
        if (collideX(dx, ((Entity) actor).getRectangle())) {
          moveOffsetX(((Entity) actor).getRectangle(), dx);
          this.memberHit = (BasePlayer) actor;
          dealDamage(this.memberHit);
          return true;
        }
        else if (collideY(dy, ((Entity) actor).getRectangle())) {
          moveOffsetY(((Entity) actor).getRectangle(), dy);
          this.memberHit = (BasePlayer) actor;
          dealDamage(this.memberHit);
          return true;
        }
      }
    }
    return false;
  }

  public Entity getOwner() {
    return this.owner;
  }

  public Entity getMemberHit() {
    return this.memberHit;
  }

  public float getSpeed() {
    return this.speed;
  }

  void dealDamage(BasePlayer victim) {
    victim.loseHealthPoints(damage);
  }
}
