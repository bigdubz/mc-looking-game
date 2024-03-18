package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Block extends Actor {


    Main main;
    Sprite image;
    boolean solid;

    public Block(Main m, int x, int y, String img, boolean solid) {
        main = m;
        image = new Sprite(new Texture(Gdx.files.internal(img)));
        image.setBounds(x, y, image.getWidth()*3.75f, image.getHeight()*3.75f);
        this.solid = solid;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        image.draw(batch);
    }

    void dispose() {
        image.getTexture().dispose();
    }
}
