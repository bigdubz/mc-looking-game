package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Main;

public class GameScreen implements Screen {

    Main main;
    public int mouseX;
    public int mouseY;

    public GameScreen(Main m) {
        main = m;
    }

    public void update() {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
