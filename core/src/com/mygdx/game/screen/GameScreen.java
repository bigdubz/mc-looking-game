package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Block;
import com.mygdx.game.Main;
import com.mygdx.game.QuadTree;
import com.mygdx.game.collidable.player.Human;

public class GameScreen extends BaseScreen {

    Human human;

    public QuadTree tree;
    public Array<Block> nearbyBlocks;
    public Array<Block> allBlocks;

    public GameScreen(Main m) {
        super(m);
    }

    @Override
    void init() {
        tree = new QuadTree(0, new Rectangle(0, 0,
                main.BLOCK_SIZE * main.MAP_WIDTH, main.BLOCK_SIZE * main.MAP_HEIGHT)); // Should be changed to
        // TiledMap tile size

        nearbyBlocks = new Array<>();
        allBlocks = new Array<>();

        human = new Human(main);
        stage.addActor(human);
    }

    @Override
    public void render(float delta) {
        stage.getCamera().position.x = human.getX();
        stage.getCamera().position.y = human.getY();
        main.mapRenderer.setView((OrthographicCamera) stage.getCamera());
        main.mapRenderer.render();
        stage.draw();
        stage.act(delta);
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
