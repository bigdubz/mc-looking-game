package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.collidable.Collidable;
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

    for (Actor actor : stage.getActors()) {
      if (actor instanceof Collidable) {
        getObjectOutOfBounds((Collidable) actor);
      }
    }

    //    Gdx.app.log("Actor Count", ""+stage.getActors().size);
    // For debugging
    //    main.sr.setProjectionMatrix(stage.getCamera().combined);
    //    main.sr.begin();
    //    for (Rectangle rect : main.solidBlocks) {
    //      main.sr.rect(rect.x, rect.y, rect.width, rect.height);
    //    }
    //    main.sr.end();
  }

  public boolean getObjectOutOfBounds(Collidable object) {
    int screenX = (int) (stage.getCamera().position.x - Gdx.graphics.getWidth() * 0.5f);
    int screenY = (int) (stage.getCamera().position.y - Gdx.graphics.getHeight() * 0.5f);
        boolean bool =
            object.getX() < screenX
                || object.getX() > screenX + Gdx.graphics.getWidth()
                || object.getY() < screenY
                || object.getY() > Gdx.graphics.getHeight();
    Gdx.app.log(
        "Out of bounds",
        ""+bool);
    return true;
  }

  @Override
  public void dispose() {
    stage.dispose();
    human.dispose();
    nearbyBlocks.clear();
  }
}
