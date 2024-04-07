package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Main;

public class MenuScreen extends BaseScreen {

  public MenuScreen(Main m) {
    super(m);
    init();
  }

  protected void init() {
    TextButton playButton = new TextButton("Play", main.skin);
    TextButton optionsButton = new TextButton("Options", main.skin);
    TextButton exitButton = new TextButton("Exit", main.skin);
    table.add(playButton);
    table.row();
    table.add(optionsButton);
    table.row();
    table.add(exitButton);
    table.setPosition(
        (Gdx.graphics.getWidth() - table.getWidth()) * 0.5f,
        (Gdx.graphics.getHeight() - table.getHeight()) * 0.5f);
    playButton.addListener(
        new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
            main.setScreen(main.gameScreen);
          }
        });
    optionsButton.addListener(
        new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
            main.setScreen(main.optionsScreen);
          }
        });
    exitButton.addListener(
        new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
            Gdx.app.exit();
          }
        });
  }

  @Override
  public void render(float delta) {
    stage.draw();
  }
}
