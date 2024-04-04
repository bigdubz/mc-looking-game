package com.mygdx.game.entity.player;

//import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.projectile.Bullet;

public abstract class GameMember extends Entity {

  private int healthPoints = 100;

  //  private final ProgressBar healthBar;

  public GameMember(Main m, String imagePath) {
    super(m, imagePath);
    //    healthBar = new ProgressBar(0, 100, 1, false, main.barStyle);
    //    main.gameScreen.stage.addActor(healthBar);
    //    healthBar.setHeight(100);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    //    healthBar.setPosition(
    //        getX() + getWidth() - healthBar.getWidth() * 0.5f, getY() + getHeight() * 1.25f);
    //    healthBar.setValue(healthPoints);
    super.draw(batch, parentAlpha);
  }

  @Override
  public void act(float delta) {
    if (!checkAlive()) {
      this.removeActor();
    }
  }

  protected void shootProjectile(float angle) {
    new Bullet(main, this, angle, 750, getX(), getY(), 10);
  }

  public void removeActor() {
    this.remove();
    //    this.healthBar.remove();
  }

  public boolean checkAlive() {
    return this.healthPoints > 0;
  }

  public void loseHealthPoints(int damage) {
    this.setHealthPoints(getHealthPoints() - damage);
  }

  public void setHealthPoints(int hp) {
    this.healthPoints = Math.max(0, hp);
  }

  public int getHealthPoints() {
    return this.healthPoints;
  }
}
