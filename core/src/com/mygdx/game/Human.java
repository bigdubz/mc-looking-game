package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Animation;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

public class Human extends Actor {

    Sprite image;
    Main main;
    Rectangle rectangle;
    Rectangle tempRect;

    // quad tree
    Rectangle nearbyRegion;
    Array<Rectangle> nearbyRects;
    QuadTree tree;
    /*
     Animation<TextureRegion> walkUp;
     Animation<TextureRegion> walkDown;
     Animation<TextureRegion> walkRight;
     Animation<TextureRegion> walkLeft;
    */

    Human(Main m) {
        main = m;
        image = new Sprite(new Texture(Gdx.files.internal("Player/down1.png")));
        rectangle = image.getBoundingRectangle();
        tempRect = new Rectangle(rectangle);
        nearbyRects = new Array<>();
        nearbyRegion = new Rectangle(rectangle);
        setTouchable(Touchable.enabled);
        tree = new QuadTree(0, new Rectangle(0, 0,
                main.BLOCK_SIZE * main.MAP_WIDTH, main.BLOCK_SIZE * main.MAP_HEIGHT));
    }

    public void load() {
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

        tree.clear();
        for (Rectangle r : main.gameScreen.allRectangles)
            tree.insert(r);

        nearbyRects.clear();
        nearbyRegion.set(getX() - 50, getY() - 50, getWidth() + 100, getHeight() + 100);
        tree.retrieve(nearbyRects, nearbyRegion);

        boolean collidedX = false;
        boolean collidedY = false;
        for (Rectangle r : nearbyRects) {
            if (!collidedX && collideX(horz, r)) collidedX = true;
            if (!collidedY && collideY(vert, r)) collidedY = true;
        }
        if (!collidedX) {
            setX(getX() + horz);
            rectangle.setX(getX());
        }
        if (!collidedY) {
            setY(getY() + vert);
            rectangle.setY(getY());
        }
    }

    boolean collideX(float dx, Rectangle block) {
        tempRect.setX(getX() + dx);
        boolean collided = tempRect.overlaps(block);
        tempRect.setX(getX());
        return collided;
    }

    boolean collideY(float dy, Rectangle block) {
        tempRect.setY(getY() + dy);
        boolean collided = tempRect.overlaps(block);
        tempRect.setY(getY());
        return collided;
    }

    void dispose() {
        image.getTexture().dispose();
    }
}
