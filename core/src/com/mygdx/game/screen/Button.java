package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Button {

    TextButton button;
    TextButtonStyle textButtonStyle;
    Skin skin;
    BitmapFont font;
    TextureAtlas buttonAtlas;
    MenuScreen screen;

    Button(MenuScreen screen) {
        this.screen = screen;
        font = new BitmapFont();
        TextButton.TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.WHITE;
        screen.stage.addActor(new TextButton("Custom Btn ", textButtonStyle));
    }
}
