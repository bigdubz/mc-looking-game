package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Animation;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Main;

public class Human extends Player {


    public Human(Main m) {
        super(m);
        image = new Sprite(new Texture(Gdx.files.internal("Player/down1.png")));
        rectangle = image.getBoundingRectangle();
        setX(getX() - 50);
        tempRect = new Rectangle(rectangle);
    }

    @Override
    public void load() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(), getY());
    }

    @Override
    public void act(float delta) {
        float vert = 0;
        float horz = 0;
        float speed = 300 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) vert += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) vert -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) horz += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) horz -= speed;

        if (horz * vert != 0) {
            horz /= (float) Math.sqrt(2);
            vert /= (float) Math.sqrt(2);
        }

        checkCollisionAndMove(horz, vert);
    }
}
