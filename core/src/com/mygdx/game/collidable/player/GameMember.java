package com.mygdx.game.collidable.player;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;
import com.mygdx.game.collidable.projectile.Bullet;

public abstract class GameMember extends Collidable {

  private int healthPoints = 100;

  public GameMember(Main m, String imagePath) {
    super(m, imagePath);
  }

  protected void shootProjectile() {
    new Bullet(
        main,
        this,
        (float)
            Math.atan2(
                Gdx.graphics.getHeight() * 0.5f - Gdx.input.getY(),
                Gdx.graphics.getWidth() * -0.5f + Gdx.input.getX()),
        500,
        getX(),
        getY(),
        10);
  }

  public boolean checkAlive() {
    return this.healthPoints > 0;
  }

  // Take damage
  public void loseHealthPoints(int damage) {
    this.setHealthPoints(getHealthPoints() - damage);
  }

  public void setHealthPoints(int hp) {
    this.healthPoints = Math.max(0, hp);
  }

  public int getHealthPoints() {
    return this.healthPoints;
  }
}
