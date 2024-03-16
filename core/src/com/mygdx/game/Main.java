package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Main extends Game {

	SpriteBatch batch;
	GameScreen gameScreen;

	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		batch = new SpriteBatch();
		setScreen(gameScreen);
	}

	@Override
	public void render () {
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	@Override
	public void dispose () {
		gameScreen.dispose();
		batch.dispose();
	}
}
