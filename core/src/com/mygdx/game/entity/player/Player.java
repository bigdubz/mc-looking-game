package com.mygdx.game.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Helper;
import com.mygdx.game.Main;
import com.mygdx.game.item.weapon.*;

public class Player extends BasePlayer {

    public Player(Main m) {
        super(m, "Player/down1.png", 300);
        // The middle of the map, temporarily
        setPosition(3200 * main.MAP_SCALE, 3200 * main.MAP_SCALE);
        inventory.addItem(new Pistol(this.main, this));
        inventory.addItem(new SMG(this.main, this));
        inventory.addItem(new Shotgun(this.main, this));
        inventory.addItem(new AK47(this.main, this));
        inventory.addItem(new AssaultRifle(this.main, this));
        inventory.addItem(new SniperRifle(this.main, this));
        selectedItem = inventory.getItem(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move(delta);
        setSelectedItemRotation(
            Helper.getAngle(
                main.SCREEN_HALF_WIDTH,
                -main.SCREEN_HALF_HEIGHT,
                Gdx.input.getX(),
                -Gdx.input.getY()
            )
        );
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            BaseWeapon weapon = getWeapon();
            if (weapon != null) {
                weapon.startReload();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            inventory.dropItem();
        }
        if (Gdx.input.isButtonPressed(0)) {
            shootProjectile();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            inventory.selectNextItem();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            inventory.selectPrevItem();
        }
    }

    private void move(float delta) {
        float dx = 0;
        float dy = 0;
        float speedWithDelta = speed * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dy += speedWithDelta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dy -= speedWithDelta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dx += speedWithDelta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dx -= speedWithDelta;
        }

        if (dx * dy != 0) {
            dx *= Helper.INVERSE_SQRT_2;
            dy *= Helper.INVERSE_SQRT_2;
        }

        checkCollisionAndMove(dx, dy);
    }
}
