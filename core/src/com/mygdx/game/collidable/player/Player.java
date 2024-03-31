package com.mygdx.game.collidable.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;
import com.mygdx.game.collidable.projectile.Bullet;
import com.mygdx.game.collidable.projectile.Projectile;

public abstract class Player extends Collidable {
  Array<Projectile> projectiles;
  Vector2 tempProjVector;

  public Player(Main m, String imagePath) {
    super(m, imagePath);
    projectiles = new Array<>();
    tempProjVector = new Vector2();
  }

  protected void shootProjectile() {
    tempProjVector.set(
        Gdx.graphics.getHeight() * 0.5f - Gdx.input.getY(),
        Gdx.graphics.getWidth() * 0.5f - Gdx.input.getX());
    projectiles.add(
        new Bullet(
            main, this, tempProjVector.angleRad() + (float) Math.PI * 0.5f, 500, getX(), getY()));
  }
}
