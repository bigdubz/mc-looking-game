package com.mygdx.game.entity.projectile;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.player.GameMember;

public abstract class Projectile extends Entity {

  Entity owner;
  Entity memberHit = null;
  int damage;
  float rotation;
  float speed;
  float speedX;
  float speedY;
  boolean collided = false;

  protected Projectile(
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
    this.speedX = speed * (float) Math.cos(rotation);
    this.speedY = speed * (float) Math.sin(rotation);
    setX(x);
    setY(y);
    float deg = (float) Math.toDegrees(rotation);
    this.rotation = deg;
    this.damage = damage;
    this.setRotation(deg);
    sprite.setRotation(deg);
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
    this.collided = this.collided || checkCollisionWithActors(dx, dy);
    setX(getX() + dx);
    rectangle.setX(getX());
    sprite.setX(getX());
    setY(getY() + dy);
    rectangle.setY(getY());
    sprite.setY(getY());
  }

  boolean checkCollisionWithActors(float dx, float dy) {
    for (Actor actor : main.gameScreen.stage.getActors()) {
      if (!(!(actor instanceof GameMember) || actor == owner)) {
        if (collideX(dx, ((Entity) actor).getRectangle())
            || collideY(dy, ((Entity) actor).getRectangle())) {
          dealDamage((GameMember) actor);
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

  void dealDamage(GameMember victim) {
    victim.loseHealthPoints(damage);
  }
}
