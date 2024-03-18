package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {

    Main main;
    Stage stage;
    Human human;
    Array<Rectangle> allRectangles;

    GameScreen(Main m) {
        main = m;
        human = new Human(main);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        allRectangles = new Array<>();
        stage.addActor(human);
    }

    public void load() {
        human.load();
        for (int y = 0; y < main.MAP_HEIGHT; y++) {
            for (int x = 0; x < main.MAP_WIDTH; x++) {
                Block block = new Block(main, x*main.BLOCK_SIZE, y*main.BLOCK_SIZE, "Blocks/A.png", false);
                stage.addActor(block);
                allRectangles.add(block.getRectangle());
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.getCamera().position.x = human.getX();
        stage.getCamera().position.y = human.getY();
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
        stage.dispose();
        human.dispose();
    }
}
