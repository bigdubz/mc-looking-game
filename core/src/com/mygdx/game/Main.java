package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MenuScreen;

public class Main extends Game {

	// MARKED FOR REMOVAL DUE TO NEW USAGE OF TILEDMAP
	public final int BLOCK_SIZE = 100;  // pixels
	public final int MAP_WIDTH = 64;  // blocks
	public final int MAP_HEIGHT = 64;

	public SpriteBatch batch;
	public GameScreen gameScreen;
	public MenuScreen menuScreen;
	public AssetManager assets;
	public Skin skin;
	public TiledMap mainMap;
	public OrthogonalTiledMapRenderer mapRenderer;
	public float mapScale = 2;
	boolean loaded = false;


	@Override
	public void create() {

		// Load skin (Loaded this first for the Label on loading screen)
		skin = new Skin();
		skin.addRegions(new TextureAtlas("Skin/pixthulhu-ui.atlas"));
		skin.load(Gdx.files.internal("Skin/pixthulhu-ui.json"));
		batch = new SpriteBatch();

		// Load asset manager
		assets = new AssetManager();
		assets.setLoader(TiledMap.class, new TmxMapLoader());

		// Queue loading then show loading screen
		load();
		setScreen(new LoadingScreen(this, skin));
	}

	void load() {
		assets.load("Map/myProject.tmx", TiledMap.class);
		assets.load("Player/down1.png", Texture.class);
	}

	void start() {
		loaded = true;
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		mainMap = assets.get("Map/myProject.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(mainMap, mapScale);
		setScreen(menuScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		if (assets.update() && !loaded) {
			start();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}
	
	@Override
	public void dispose () {
		gameScreen.dispose();
		batch.dispose();
		skin.dispose();
		mainMap.dispose();
		mapRenderer.dispose();
		assets.dispose();
	}
}
