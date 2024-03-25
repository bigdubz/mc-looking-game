package com.mygdx.game.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Button {

    TextButton button;

    Button(Skin skin, String text) {
        button = new TextButton(text, skin);
    }
}
