package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Block extends Actor {

  Main main;
  Sprite image;
  public Rectangle rectangle;
  public boolean solid;

  public Block(Main m, int x, int y, String img, boolean solid) {
    main = m;
    image = new Sprite(new Texture(Gdx.files.internal(img)));
    this.solid = solid;
    setBounds(x, y, main.BLOCK_SIZE, main.BLOCK_SIZE);
    image.setBounds(x, y, getWidth(), getHeight());
    rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    image.draw(batch);
  }

  public Rectangle getRectangle() {
    return this.rectangle;
  }

  public void dispose() {
    image.getTexture().dispose();
  }
}
