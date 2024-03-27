package com.mygdx.game.collidable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.Block;
import com.mygdx.game.Main;

public abstract class Collidable extends Actor {

    protected Main main;
    protected Sprite image;
    protected Rectangle tempRect;
    public Rectangle rectangle;

    protected Collidable(Main m) {
        main = m;
        setTouchable(Touchable.enabled);
    }

    protected void checkCollisionAndMove(float horz, float vert) {

        main.gameScreen.tree.clear();
//        for (Block r : main.gameScreen.allBlocks)
//            if (r.solid) main.gameScreen.tree.insert(r);

        for (RectangleMapObject rect : main.solidBlocks.getByType(RectangleMapObject.class))
            main.gameScreen.tree.insert(rect.getRectangle());

        main.gameScreen.nearbyBlocks.clear();
        main.gameScreen.tree.retrieve(main.gameScreen.nearbyBlocks, rectangle);

        boolean collidedX = false;
        boolean collidedY = false;
        for (Rectangle r : main.gameScreen.nearbyBlocks) {
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

    boolean collideX(float dx, Rectangle tempRect, Rectangle block) {
        tempRect.setX(getX() + dx);
        boolean collided = tempRect.overlaps(block);
        tempRect.setX(getX());
        return collided;
    }

    boolean collideY(float dy, Rectangle tempRect, Rectangle block) {
        tempRect.setY(getY() + dy);
        boolean collided = tempRect.overlaps(block);
        tempRect.setY(getY());
        return collided;
    }

    public void dispose() {
        image.getTexture().dispose();
    }
}
