package com.mygdx.game.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.Block;
import com.mygdx.game.Main;

public abstract class Player extends Actor {

    Sprite image;
    Main main;
    Rectangle rectangle;
    Rectangle tempRect;


    public Player(Main m) {
        main = m;
        setTouchable(Touchable.enabled);
    }

    void checkCollisionAndMove(float horz, float vert) {

        main.gameScreen.tree.clear();
        for (Block r : main.gameScreen.allBlocks)
            if (r.solid) main.gameScreen.tree.insert(r);

        main.gameScreen.nearbyBlocks.clear();
        main.gameScreen.tree.retrieve(main.gameScreen.nearbyBlocks, rectangle);

        boolean collidedX = false;
        boolean collidedY = false;
        for (Block r : main.gameScreen.nearbyBlocks) {
            if (!collidedX && collideX(horz, tempRect, r)) collidedX = true;
            if (!collidedY && collideY(vert, tempRect, r)) collidedY = true;
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


    boolean collideX(float dx, Rectangle tempRect, Block block) {
        tempRect.setX(getX() + dx);
        boolean collided = tempRect.overlaps(block.getRectangle());
        tempRect.setX(getX());
        return collided;
    }

    boolean collideY(float dy, Rectangle tempRect, Block block) {
        tempRect.setY(getY() + dy);
        boolean collided = tempRect.overlaps(block.getRectangle());
        tempRect.setY(getY());
        return collided;
    }

    public void dispose() {
        image.getTexture().dispose();
    }
}
