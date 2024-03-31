package com.mygdx.game.collidable.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;

public class NPC extends Player {

  public NPC(Main m) {
    super(m, "Player/Skeleton/npcWalk1.png");

    // The middle of the map, temporarily
    setBounds(
        3100 * main.MAP_SCALE, 3100 * main.MAP_SCALE, rectangle.getWidth(), rectangle.getHeight());
    halfWidth = getWidth() * 0.5f;
    halfHeight = getHeight() * 0.5f;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.draw(image, getX(), getY());
  }

  @Override
  public void act(float delta) {
    followHuman(delta);
  }

  void followHuman(float delta) {
    // TEMPORARY
    Human p = main.gameScreen.human;
    float speed = 300 * delta;
    float dx = p.getX() - getX() > 0 ? speed : -speed;
    float dy = p.getY() - getY() > 0 ? speed : -speed;
    if (Math.abs(p.getX() - getX()) < 0.5 * main.BLOCK_SIZE) {
      dx = 0;
    }
    if (Math.abs(p.getY() - getY()) < 0.5 * main.BLOCK_SIZE) {
      dy = 0;
    }
    if (dx != 0 && dy != 0) {
      dx *= main.INVERSE_SQRT_2;
      dy *= main.INVERSE_SQRT_2;
    }
    checkCollisionAndMove(dx, dy);
  }
}
