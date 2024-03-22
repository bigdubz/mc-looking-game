package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
    OrthogonalTiledMapRenderer renderer;
    float unitScale = 2;
    TiledMap map;

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
        map = new TmxMapLoader().load("tileset/MyMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.getCamera().position.x = human.getX();
        stage.getCamera().position.y = human.getY();
        renderer.setView((OrthographicCamera) stage.getCamera());
        renderer.render();
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
        map.dispose();
    }
}
