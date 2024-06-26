package com.mygdx.game.screen.gamescreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.NPC;
import com.mygdx.game.entity.player.Player;
import com.mygdx.game.screen.BaseScreen;

public class GameScreen extends BaseScreen {

    public Player player;
    public NPC npc;
    public Array<Rectangle> nearbyBlocks;
    public UI ui;

    // Testing
    int[] layers = new int[] { 0, 1 };
    int[] layers2 = new int[] { 2, 3, 4 };

    public GameScreen(Main m) {
        super(m);
    }

    @Override
    public void init() {
        nearbyBlocks = new Array<>();
        player = new Player(main);
        npc = new NPC(main);
        ui = new UI(main, player);
    }

    @Override
    public void render(float delta) {
        stage.getCamera().position.x = player.getX() + player.getHalfWidth();
        stage.getCamera().position.y = player.getY() + player.getHalfHeight();

        main.mapRenderer.setView((OrthographicCamera) stage.getCamera());
        main.mapRenderer.render(layers);
        stage.draw();
        stage.act(delta);
        main.mapRenderer.render(layers2);
        ui.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        ui.getStage().getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
        player.dispose();
        nearbyBlocks.clear();
    }
}
