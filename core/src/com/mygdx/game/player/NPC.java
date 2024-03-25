package com.mygdx.game.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;

public class NPC extends Player {


    public NPC(Main m) {
        super(m);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, getX(), getY());
    }

    @Override
    public void act(float delta) {
    }
}
