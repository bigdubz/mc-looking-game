package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Helper;
import com.mygdx.game.Main;
import com.mygdx.game.item.weapon.Pistol;

public class NPC extends BasePlayer {

    public NPC(Main m) {
        super(m, "Player/Skeleton/npcWalk1.png", 250);
        // The middle of the map, temporarily
        this.setPosition(3100 * main.MAP_SCALE, 3100 * main.MAP_SCALE);
        inventory.addItem(new Pistol(main, this));
        selectedItem = inventory.getItem(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setSelectedItemRotation(
            Helper.getAngle(
                getX(),
                getY(),
                main.gameScreen.player.getX(),
                main.gameScreen.player.getY()
            )
        );
        followHuman(delta);
    }

    @Override
    public void drawHealthBar(ShapeRenderer sr) {
        int w = 50;
        int h = 10;
        main.sr.setProjectionMatrix(main.gameScreen.stage.getCamera().combined);
        sr.setColor(1,0,0,1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(getX() + (getWidth() - w)*0.5f, getY() + getHeight() + 10, w*(getHealthPoints()/100f), h);
        sr.end();
        sr.begin();
        sr.setColor(0.2f,0.2f,0.2f,1);
        sr.rect(getX() + (getWidth() - w)*0.5f, getY() + getHeight() + 10, w, h);
        sr.end();
    }

    void followHuman(float delta) {
        // TEMPORARY
        Player p = main.gameScreen.player;
        float speedWithDelta = speed * delta;
        float dx = p.getX() - getX() > 0 ? speedWithDelta : -speedWithDelta;
        float dy = p.getY() - getY() > 0 ? speedWithDelta : -speedWithDelta;
        if (Math.abs(p.getX() - getX()) < 0.5 * main.BLOCK_SIZE) {
            dx = 0;
        }
        if (Math.abs(p.getY() - getY()) < 0.5 * main.BLOCK_SIZE) {
            dy = 0;
        }
        if (dx * dy != 0) {
            dx *= Helper.INVERSE_SQRT_2;
            dy *= Helper.INVERSE_SQRT_2;
        }
        checkCollisionAndMove(dx, dy);
        shootProjectile();
    }
}
