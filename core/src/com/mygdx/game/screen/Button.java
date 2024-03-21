package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Button {

    TextButton button;
    Skin skin;
    BitmapFont font;
    TextureAtlas buttonAtlas;
    MenuScreen screen;

    Button(MenuScreen screen) {
        this.screen = screen;
        font = new BitmapFont();
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("Skin/pixthulhu-ui.atlas")));
        skin.load(Gdx.files.internal("Skin/pixthulhu-ui.json"));
        button = new TextButton("Button Testing", skin);
        button.setPosition((Gdx.graphics.getWidth()-button.getWidth())/2f, (Gdx.graphics.getHeight()-button.getHeight())/2f);
        screen.stage.addActor(button);
    }
}
