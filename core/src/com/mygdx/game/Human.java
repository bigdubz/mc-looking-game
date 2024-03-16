package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
        setBounds(image.getX(), image.getY(), image.getWidth(), image.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch);
        batch.draw(image, 100, 100);
    }
}
