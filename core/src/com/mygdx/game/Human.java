package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Human extends Actor {

    Sprite image;
    Main main;

    Human(Main m) {
        main = m;
        image = new Sprite(new Texture(Gdx.files.internal("Player/down1.png")));
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch);
    }

    void update(float delta) {
        float vert = 0;
        float horz = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) vert += 100 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) vert -= 100 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) horz -= 100 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) horz += 100 * delta;

        if (horz * vert != 0) {
            horz /= (float) Math.sqrt(2);
            vert /= (float) Math.sqrt(2);
        }
        image.setY(image.getY() + vert);
        image.setX(image.getX() + horz);
    }
}
