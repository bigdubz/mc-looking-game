package com.mygdx.game.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Block;
import com.mygdx.game.Main;
import com.mygdx.game.QuadTree;

public abstract class Player extends Actor {

    Sprite image;
    Main main;
    Rectangle rectangle;
    Rectangle tempRect;

    // quad tree
    QuadTree tree;
    Rectangle nearbyRegion;
    Array<Block> nearbyBlocks;
    /*
     Animation<TextureRegion> walkUp;
     Animation<TextureRegion> walkDown;
     Animation<TextureRegion> walkRight;
     Animation<TextureRegion> walkLeft;
    */


    public Player(Main m) {
        main = m;
    }

    public abstract void load();


    void checkCollisionAndMove(float horz, float vert) {

        tree.clear();
        for (Block r : main.gameScreen.allBlocks)
            if (r.solid) tree.insert(r);

        nearbyBlocks.clear();
        tree.retrieve(nearbyBlocks, rectangle);

        boolean collidedX = false;
        boolean collidedY = false;
        for (Block r : nearbyBlocks) {
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
}
