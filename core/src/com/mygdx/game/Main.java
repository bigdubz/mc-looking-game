package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		super.render();
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
