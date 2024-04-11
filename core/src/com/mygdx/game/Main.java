package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screen.LoadingScreen;
import com.mygdx.game.screen.MenuScreen;
import com.mygdx.game.screen.OptionsScreen;
import com.mygdx.game.screen.gamescreen.GameScreen;

public class Main extends Game {

    public final float MAP_SCALE = 2;
    public final float BLOCK_SIZE = 32 * MAP_SCALE; // pixels
    //    public final float INVERSE_BLOCK_SIZE = 1 / BLOCK_SIZE;
    public float SCREEN_HALF_WIDTH;
    public float SCREEN_HALF_HEIGHT;
    public SpriteBatch batch;
    public ShapeRenderer sr;
    public GameScreen gameScreen;
    public MenuScreen menuScreen;
    public OptionsScreen optionsScreen;
    public AssetManager assets;
    public Skin skin;
    public TiledMap mainMap;
    public Array<Rectangle> solidBlocks;
    public OrthogonalTiledMapRenderer mapRenderer;
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
        SCREEN_HALF_WIDTH = Gdx.graphics.getWidth() * 0.5f;
        SCREEN_HALF_HEIGHT = Gdx.graphics.getHeight() * 0.5f;
        setScreen(new LoadingScreen(this));
    }

    void load() {
        //    assets.load("Map/myProject.tmx", TiledMap.class);
        assets.load("Map/extruded_test.tmx", TiledMap.class);
        assets.load("Player/down1.png", Texture.class);
        assets.load("Player/Skeleton/npcWalk1.png", Texture.class);
        assets.load("Items/bullet.png", Texture.class);
        assets.load("Items/Pistol.png", Texture.class);
        assets.load("Items/Submachine.png", Texture.class);
        assets.load("Items/Shotgun.png", Texture.class);
        assets.load("Items/AK47.png", Texture.class);
        assets.load("Items/SG553.png", Texture.class);
        assets.load("Items/AWP.png", Texture.class);
    }

    void start() {
        loaded = true;
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        optionsScreen = new OptionsScreen(this);
        //    mainMap = assets.get("Map/myProject.tmx");
        mainMap = assets.get("Map/extruded_test.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(mainMap, MAP_SCALE);
        solidBlocks = new Array<>();
        for (RectangleMapObject rectMapObject : mainMap
            .getLayers()
            .get("collision")
            .getObjects()
            .getByType(RectangleMapObject.class)) {
            solidBlocks.add(
                new Rectangle(
                    rectMapObject.getRectangle().x * MAP_SCALE,
                    rectMapObject.getRectangle().y * MAP_SCALE,
                    (rectMapObject.getRectangle().width + 2) * MAP_SCALE,
                    (rectMapObject.getRectangle().height + 2) * MAP_SCALE
                )
            );
        }
        gameScreen.init();
        setScreen(menuScreen);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
        if (!(loaded || !assets.update())) {
            start();
        }
    }

    @Override
    public void dispose() {
        gameScreen.dispose();
        batch.dispose();
        skin.dispose();
        mainMap.dispose();
        mapRenderer.dispose();
        assets.dispose();
    }
}
