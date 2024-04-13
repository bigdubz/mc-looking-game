package com.mygdx.game.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;

public abstract class BaseItem extends Actor {

    protected Main main;
    protected BasePlayer holder;
    protected Sprite sprite;
    protected String itemName;

    public BaseItem(
        Main m,
        BasePlayer holder,
        String imagePath,
        String itemName
    ) {
        this.main = m;
        this.holder = holder;
        this.sprite = new Sprite(
            this.main.assets.get(imagePath, Texture.class)
        );
        setBounds(
            sprite.getX(),
            sprite.getY(),
            sprite.getWidth(),
            sprite.getHeight()
        );
        this.itemName = itemName;
        this.main.gameScreen.getStage().addActor(this);
    }

    public abstract void itemDeselected();

    public abstract void itemSelected();

    @Override
    public String getName() {
        return itemName;
    }
}
