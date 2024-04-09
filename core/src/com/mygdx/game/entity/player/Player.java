package com.mygdx.game.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Main;
import com.mygdx.game.item.weapon.BaseWeapon;
import com.mygdx.game.item.weapon.Pistol;
import com.mygdx.game.item.weapon.SMG;
import com.mygdx.game.item.weapon.Shotgun;

public class Player extends BasePlayer {

    public Player(Main m) {
        super(m, "Player/down1.png");
        // The middle of the map, temporarily
        this.setPosition(3200 * main.MAP_SCALE, 3200 * main.MAP_SCALE);
        inventory.addItem(new Pistol(this.main, this));
        inventory.addItem(new SMG(this.main, this));
        inventory.addItem(new Shotgun(this.main, this));
        this.selectedItem = inventory.getItem(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        move(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            BaseWeapon weapon = getWeapon();
            if (weapon != null) {
                weapon.startReload();
            }
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
        float vert = 0;
        float horz = 0;
        float speed = 300 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            vert += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            vert -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            horz += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            horz -= speed;
        }

        if (horz * vert != 0) {
            horz *= main.INVERSE_SQRT_2;
            vert *= main.INVERSE_SQRT_2;
        }

        checkCollisionAndMove(horz, vert);
    }
}
