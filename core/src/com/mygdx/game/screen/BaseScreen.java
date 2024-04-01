package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Main;

public abstract class BaseScreen implements Screen {

  Main main;
  public Table table;
  public Stage stage;

  BaseScreen(Main m) {
    main = m;
    table = new Table(main.skin);
    stage = new Stage(new ScreenViewport());
    stage.addActor(table);
  }

  abstract void init();

  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    stage.dispose();
  }
}
