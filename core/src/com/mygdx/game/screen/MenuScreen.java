package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Main;

public class MenuScreen implements Screen {

    Main main;
    Stage stage;
    Skin skin;
    Table table;

    public MenuScreen(Main m) {
        main = m;
        stage = new Stage();
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("Skin/pixthulhu-ui.atlas")));
        skin.load(Gdx.files.internal("Skin/pixthulhu-ui.json"));
        table = new Table(skin);
        initButtons();
        stage.addActor(table);
    }

    void initButtons() {
        Button playButton = new Button(this, skin, "Play");
        Button optionsButton = new Button(this, skin, "Options");
        Button exitButton = new Button(this, skin, "Exit");
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
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
    }
}
