package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Main;

public class LoadingScreen extends BaseScreen {

  Label label;

  public LoadingScreen(Main m, Skin skin) {
    super(m);
    label = new Label("Loading...", skin);
    label.setFontScale(2);
    table.add(label);
    table.setPosition(
        (Gdx.graphics.getWidth() - table.getWidth()) / 2f,
        (Gdx.graphics.getHeight() - table.getHeight()) / 2f);
  }

  @Override
  void init() {}

  @Override
  public void render(float delta) {
    label.setText("Loading... " + (int) (main.assets.getProgress() * 100) + "%");
    stage.draw();
  }
}
