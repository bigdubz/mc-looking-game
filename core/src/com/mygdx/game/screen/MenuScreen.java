package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Main;

public class MenuScreen implements Screen {

    Main main;
    Stage stage;

    public MenuScreen(Main m) {
        main = m;
        stage = new Stage();
        Button button = new Button(this);
    }


    @Override
    public void show() {

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

    }
}
