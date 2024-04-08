package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Main;

public class OptionsScreen extends BaseScreen {

  public OptionsScreen(Main m) {
    super(m);
    init();
  }

  @Override
  protected void init() {
    TextButton backButton = new TextButton("Back", main.skin);
    table.add(backButton);
    table.setPosition(
      (Gdx.graphics.getWidth() - table.getWidth()) * 0.5f,
      (Gdx.graphics.getHeight() - table.getHeight()) * 0.5f
    );
    backButton.addListener(
      new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
          main.setScreen(main.menuScreen);
        }
      }
    );
  }

  @Override
  public void render(float delta) {
    stage.act();
    stage.draw();
  }
}
