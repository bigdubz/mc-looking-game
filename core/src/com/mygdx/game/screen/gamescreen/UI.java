package com.mygdx.game.screen.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;
import com.mygdx.game.entity.player.Player;

public class UI {

    private final Main main;
    private final Player player;
    private final ScrollPane invScrollPane;
    private final Stage stage;
    protected List<String[]> list;
    Table table;

    public UI(Main m, Player player) {
        this.stage = new Stage(new ScreenViewport());
        this.main = m;
        this.player = player;
        this.table = new Table(main.skin);
        list = new List<>(main.skin);
        invScrollPane = new ScrollPane(list, main.skin);
        table.add(invScrollPane);
        table.setWidth(500);
        table.setHeight(500);
        table.setPosition(0, Gdx.graphics.getHeight() - table.getHeight());
        stage.getCamera().position.x = main.SCREEN_HALF_WIDTH;
        stage.getCamera().position.y = main.SCREEN_HALF_HEIGHT;
        stage.addActor(table);
    }

    public void draw() {
        drawInventory();
        for (Actor player : main.gameScreen.stage.getActors()) {
            if (player instanceof BasePlayer) {
                ((BasePlayer) player).drawHealthBar(main.sr);
            }
        }
    }

    public void drawInventory() {
        if (player.getInvOpen()) {
            main.batch.setProjectionMatrix(stage.getCamera().combined);
            list.setItems(player.getItems());
            stage.draw();
        }
    }

    public Stage getStage() {
        return this.stage;
    }
}
