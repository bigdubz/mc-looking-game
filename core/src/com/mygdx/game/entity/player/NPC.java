package com.mygdx.game.entity.player;

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
