package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Main;

public class MenuScreen extends BaseScreen {


    public MenuScreen(Main m) {
        super(m);
    }

    void init() {
        Button playButton = new Button(this, main.skin, "Play");
        Button optionsButton = new Button(this, main.skin, "Options");
        Button exitButton = new Button(this, main.skin, "Exit");
        table.add(playButton.button);
        table.row();
        table.add(optionsButton.button);
        table.row();
        table.add(exitButton.button);
        table.setPosition(
                (Gdx.graphics.getWidth()-table.getWidth())/2f,
                (Gdx.graphics.getHeight()-table.getHeight())/2f
        );
        playButton.button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(main.gameScreen);
            }
        });
        exitButton.button.addListener(new ClickListener() {
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
