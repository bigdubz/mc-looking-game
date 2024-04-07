package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.player.inventory.Inventory;
import com.mygdx.game.item.BaseItem;
import com.mygdx.game.item.weapon.BaseWeapon;

public abstract class BasePlayer extends Entity {

  private int healthPoints = 100;
  final Inventory inventory;
  BaseItem selectedItem;

  public BasePlayer(Main m, String imagePath) {
    super(m, imagePath);
    inventory = new Inventory(this.main);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
  }

  @Override
  public void act(float delta) {
    if (!checkAlive()) {
      this.removeActor();
    }
  }

  protected void shootProjectile(float angle) {
    BaseWeapon weapon = getWeapon();
    if (weapon != null) {
      weapon.shootProjectile(angle);
    }
  }

  public void removeActor() {
    this.remove();
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

  public BaseWeapon getWeapon() {
    if (!(selectedItem == null || !(selectedItem instanceof BaseWeapon))) {
      return (BaseWeapon) selectedItem;
    }
    return null;
  }
}
