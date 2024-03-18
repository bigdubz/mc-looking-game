package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Animation;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Human extends Actor {

    Texture image;
    Main main;
    Rectangle rectangle;
    Rectangle tempRect;
    /*
     Animation<TextureRegion> walkUp;
     Animation<TextureRegion> walkDown;
     Animation<TextureRegion> walkRight;
     Animation<TextureRegion> walkLeft;
    */

    Human(Main m) {
        main = m;
        image = new Texture(Gdx.files.internal("Player/down1.png"));
        rectangle = new Rectangle(getX(), getY(), image.getWidth(), image.getHeight());
        tempRect = new Rectangle(rectangle);
        setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(), getY());
    }

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

        if (!collideX(horz)) {
            setX(getX() + horz);
            rectangle.setX(getX());
        }
        if (!collideY(vert)) {
            setY(getY() + vert);
            rectangle.setY(getY());
        }
    }

    boolean collideX(float dx/*, Block block*/) {
        tempRect.setX(getX() + dx);
        boolean collided = tempRect.overlaps(main.gameScreen.bTest.rectangle);
        tempRect.setX(getX());
        return collided;
    }

    boolean collideY(float dy/*, Block block*/) {
        tempRect.setY(getY() + dy);
        boolean collided = tempRect.overlaps(main.gameScreen.bTest.rectangle);
        tempRect.setY(getY());
        return collided;
    }

    void dispose() {
        image.dispose();
    }
}
