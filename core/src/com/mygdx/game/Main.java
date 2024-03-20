package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Main extends Game {

	public SpriteBatch batch;
	ShapeRenderer sr;
	public GameScreen gameScreen;
	public final int BLOCK_SIZE = 100;  // pixels
	public final int MAP_WIDTH = 64;  // blocks
	public final int MAP_HEIGHT = 64;


	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		gameScreen.load();
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		gameScreen.dispose();
		batch.dispose();
		sr.dispose();
	}
}
