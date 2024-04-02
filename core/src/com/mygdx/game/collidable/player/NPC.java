package com.mygdx.game.collidable.player;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Main;

public class NPC extends GameMember {

  public NPC(Main m) {
    super(m, "Player/Skeleton/npcWalk1.png");

    // The middle of the map, temporarily
    setPosition(3100 * main.MAP_SCALE, 3100 * main.MAP_SCALE);
    halfWidth = getWidth() * 0.5f;
    halfHeight = getHeight() * 0.5f;
  }

  @Override
  public void act(float delta) {
    if (!checkAlive()) this.remove();
    followHuman(delta);
  }

  void followHuman(float delta) {
    // TEMPORARY
    Player p = main.gameScreen.player;
    float speed = 300 * delta;
    float dx = p.getX() - getX() > 0 ? speed : -speed;
    float dy = p.getY() - getY() > 0 ? speed : -speed;
    if (Math.abs(p.getX() - getX()) < 0.5 * main.BLOCK_SIZE) {
      dx = 0;
    }
    if (Math.abs(p.getY() - getY()) < 0.5 * main.BLOCK_SIZE) {
      dy = 0;
    }
    if (!(dx == 0 || dy == 0)) {
      dx *= main.INVERSE_SQRT_2;
      dy *= main.INVERSE_SQRT_2;
    }
    checkCollisionAndMove(dx, dy);
  }
}
