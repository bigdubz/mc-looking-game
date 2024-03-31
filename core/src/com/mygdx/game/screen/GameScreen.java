package com.mygdx.game.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.player.Human;
import com.mygdx.game.collidable.player.NPC;

public class GameScreen extends BaseScreen {

  public Human human;
  public NPC npc;
  public Array<Rectangle> nearbyBlocks;

  // Testing
  int[] layers = new int[] {0, 1};
  int[] layers2 = new int[] {2, 3, 4};

  public GameScreen(Main m) {
    super(m);
  }

  @Override
  public void init() {
    nearbyBlocks = new Array<>();
    human = new Human(main);
    npc = new NPC(main);
  }

  @Override
  public void render(float delta) {
    stage.getCamera().position.x = human.getX() + human.getHalfWidth();
    stage.getCamera().position.y = human.getY() + human.getHalfHeight();

    main.mapRenderer.setView((OrthographicCamera) stage.getCamera());
    main.mapRenderer.render(layers);
    stage.draw();
    stage.act(delta);
    main.mapRenderer.render(layers2);

    // For debugging
    //    main.sr.setProjectionMatrix(stage.getCamera().combined);
    //    main.sr.begin();
    //    for (Rectangle rect : main.solidBlocks) {
    //      main.sr.rect(rect.x, rect.y, rect.width, rect.height);
    //    }
    //    main.sr.end();
  }

  @Override
  public void dispose() {
    stage.dispose();
    human.dispose();
    nearbyBlocks.clear();
  }
}
