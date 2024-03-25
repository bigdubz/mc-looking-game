package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Main;

public class OptionsScreen extends BaseScreen {

    Main main;

    public OptionsScreen(Main m) {
        super(m);
    }

    @Override
    void init() {
        Button exitButton = new Button(main.skin, "Exit");
        table.add(exitButton.button);
        table.setPosition(
                (Gdx.graphics.getWidth()-table.getWidth())/2f,
                (Gdx.graphics.getHeight()-table.getHeight())/2f
        );
        exitButton.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(main.menuScreen);
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }
}
