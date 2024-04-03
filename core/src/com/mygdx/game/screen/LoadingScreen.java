package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.mygdx.game.Main;

public class LoadingScreen extends BaseScreen {

  Label label;
  ProgressBar progressBar;

  public LoadingScreen(Main m) {
    super(m);
    init();
  }

  @Override
  void init() {
    label = new Label("Loading...", main.skin);
    progressBar = new ProgressBar(0, 100, 1, false, main.barStyle);

    label.setFontScale(2);
    table.add(label);
    table.row();
    table.setPosition(
        (Gdx.graphics.getWidth() - table.getWidth()) / 2f,
        (Gdx.graphics.getHeight() - table.getHeight()) / 2f);
  }

  @Override
  public void render(float delta) {
    label.setText("Loading... " + (int) (main.assets.getProgress() * 100) + "%");
    stage.draw();
  }
}
