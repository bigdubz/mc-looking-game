package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Block;
import com.mygdx.game.Main;
import com.mygdx.game.QuadTree;
import com.mygdx.game.player.Human;

public class GameScreen implements Screen {

    Main main;
    Stage stage;
    Human human;

    // quadtree
    public QuadTree tree;
    public Array<Block> nearbyBlocks;
    public Array<Block> allBlocks;

    public GameScreen(Main m) {
        main = m;
        tree = new QuadTree(0, new Rectangle(0, 0,
                main.BLOCK_SIZE * main.MAP_WIDTH, main.BLOCK_SIZE * main.MAP_HEIGHT));
        nearbyBlocks = new Array<>();
        human = new Human(main);
        stage = new Stage(new ScreenViewport());
        allBlocks = new Array<>();
        stage.addActor(human);
    }

    public void load() {
        human.load();
        for (int y = 0; y < main.MAP_HEIGHT; y++) {
            for (int x = 0; x < main.MAP_WIDTH; x++) {
                Block block = new Block(main, x*main.BLOCK_SIZE, y*main.BLOCK_SIZE, "Blocks/A.png", true);
                stage.addActor(block);
                allBlocks.add(block);
            }
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.getCamera().position.x = human.getX();
        stage.getCamera().position.y = human.getY();
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
        for (Block block : allBlocks) block.dispose();
        allBlocks.clear();
        tree.clear();
        nearbyBlocks.clear();
    }
}
