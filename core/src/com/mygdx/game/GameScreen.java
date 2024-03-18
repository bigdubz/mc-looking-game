package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

    Main main;
    Stage stage;
    Human test;
    Block bTest;

    GameScreen(Main m) {
        main = m;
        test = new Human(main);
        bTest = new Block(main, 200, 200, "Blocks/A.png", true);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.addActor(bTest);
        stage.addActor(test);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.getCamera().position.x = test.getX();
        stage.getCamera().position.y = test.getY();
        main.sr.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
        bTest.dispose();
        test.dispose();
    }
}
