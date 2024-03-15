package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.map.Block;
import com.mygdx.game.map.Map;
import com.mygdx.game.player.*;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.state.DayNightCycle;
import com.mygdx.game.state.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Main extends Game {

	SpriteBatch batch;
	public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
	public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	public static final int BLOCK_SIZE = 120;
	public static String dir = System.getProperty("user.dir");
	public static int mouseX;
	public static int mouseY;
	public Human player;
	public DayNightCycle cycle;
	public LinkedList<Player> allPlayers = new LinkedList<>();
	public Map map;
	public GameState gameState;
	GameScreen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this);
		setScreen(new MainMenuScreen(this));
		start();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	void start() {
		map = new Map(this);
		player = new Human(this);
		gameState = new GameState(this, 60);
		cycle = new DayNightCycle(this, 60, 0, 60, 120);
		allPlayers.add(player);
	}

	public void newGame() {
		allPlayers.clear();
		map = new Map(this);
		player = new Human(this);
		gameState = new GameState(this, 60);
		cycle = new DayNightCycle(this, 60, 0, 60, 120);
		allPlayers.add(player);
	}

	void update() {
		map.updateWorldMap();
		player.update();
		cycle.update();
		// set game over here
		gameState.update();
	}


	void draw(Graphics2D g) {
		map.drawMap(g);
		gameState.draw(g);
		player.draw(g);
		cycle.drawDim(g);
		player.ui.draw(g);
	}

	public LinkedList<int[]> getEmptyPoints() {
		LinkedList<int[]> emptyPoints = new LinkedList<>();
		int x = 0;
		int y = 0;
		for (BufferedImage[] row : map.map) {
			for (BufferedImage img : row) {
				if (img == map.c || img == map.F || img == map.l) {
					emptyPoints.add(new int[]{x, y});
				}
				x++;
			}
			x = 0;
			y++;
		}
		return emptyPoints;
	}

	public double[] getRandomEmptyPoint() {
		LinkedList<int[]> emptyPoints = getEmptyPoints();
		int[] randomPoint = emptyPoints.get(getRandomInt(0, emptyPoints.size()));
		map.updateWorldMap();
		double X = map.worldX + randomPoint[0];
		double Y = map.worldY + randomPoint[1];
		return new double[]{X, Y};
	}

	public int getRandomInt(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public double[] getMouseCoordinates() {
		return new double[]{mouseX, mouseY};
	}

	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}

	public int getCol(double X) {
		return (int) Math.abs(Math.floor(map.worldX - X)) - 1;
	}

	public int getRow(double Y) {
		return (int) Math.abs(Math.floor(map.worldY - Y)) - 1;
	}

	public Block getBlock(double X, double Y) {
		return map.getPos(getRow(Y), getCol(X));
	}
}
