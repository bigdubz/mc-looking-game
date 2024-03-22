package com.mygdx.game.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Button {

    TextButton button;
    MenuScreen screen;

    Button(MenuScreen screen, Skin skin, String text) {
        this.screen = screen;
        button = new TextButton(text, skin);
    }
}
