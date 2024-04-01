package com.mygdx.game.collidable.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Main;

public class Human extends Player {

  public Human(Main m) {
    super(m, "Player/down1.png");

    // The middle of the map, temporarily
    setBounds(
        3200 * main.MAP_SCALE, 3200 * main.MAP_SCALE, rectangle.getWidth(), rectangle.getHeight());
    halfWidth = getWidth() * 0.5f;
    halfHeight = getHeight() * 0.5f;
  }

  @Override
  public void act(float delta) {
    move(delta);
    if (Gdx.input.isButtonJustPressed(0)) shootProjectile();
  }

  private void move(float delta) {
    float vert = 0;
    float horz = 0;
    float speed = 300 * delta;
    if (Gdx.input.isKeyPressed(Input.Keys.W)) vert += speed;
    if (Gdx.input.isKeyPressed(Input.Keys.S)) vert -= speed;
    if (Gdx.input.isKeyPressed(Input.Keys.D)) horz += speed;
    if (Gdx.input.isKeyPressed(Input.Keys.A)) horz -= speed;

    if (horz * vert != 0) {
      horz *= main.INVERSE_SQRT_2;
      vert *= main.INVERSE_SQRT_2;
    }

    checkCollisionAndMove(horz, vert);
  }
}
