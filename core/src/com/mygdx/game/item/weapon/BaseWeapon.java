package com.mygdx.game.item.weapon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;
import com.mygdx.game.item.BaseItem;

public abstract class BaseWeapon extends BaseItem {

  public BasePlayer holder;
  public int minDamage;
  public int maxDamage;
  public int fireRate;
  public int projectileSpeed;
  public int spread;
  public int shotsFired;
  public int magSize;
  public int currentAmmo;
  public int reloadTime;
  public long reloadStart;
  boolean isReloading = false;

  public BaseWeapon(
      Main m,
      String imagePath,
      BasePlayer holder,
      int minDamage,
      int maxDamage,
      int fireRate,
      int projectileSpeed,
      int spread,
      int shotsFired,
      int magSize,
      int currentAmmo,
      int reloadTime,
      String itemName) {
    super(m, imagePath, itemName);
    this.holder = holder;
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
    this.fireRate = fireRate;
    this.projectileSpeed = projectileSpeed;
    this.spread = spread;
    this.shotsFired = shotsFired;
    this.magSize = magSize;
    this.currentAmmo = currentAmmo;
    this.reloadTime = reloadTime;
  }

  public abstract void shootProjectile(float angle);

  @Override
  public void act(float delta) {
    if (!(getElapsedReloadTime() < reloadTime || !isReloading)) {
      reload();
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {

  }

  public void reload() {
    this.setCurrentAmmo(this.getMagSize());
    this.isReloading = false;
  }

  public void startReload() {
    if (this.isReloading) return;
    this.setReloadStart(System.currentTimeMillis());
    this.isReloading = true;
  }

  public long getElapsedReloadTime() {
    return System.currentTimeMillis() - this.getReloadStart();
  }

  public int getMinDamage() {
    return minDamage;
  }

  public void setMinDamage(int minDamage) {
    this.minDamage = minDamage;
  }

  public int getMaxDamage() {
    return maxDamage;
  }

  public void setMaxDamage(int maxDamage) {
    this.maxDamage = maxDamage;
  }

  public int getFireRate() {
    return fireRate;
  }

  public void setFireRate(int fireRate) {
    this.fireRate = fireRate;
  }

  public int getProjectileSpeed() {
    return projectileSpeed;
  }

  public void setProjectileSpeed(int projectileSpeed) {
    this.projectileSpeed = projectileSpeed;
  }

  public int getSpread() {
    return spread;
  }

  public void setSpread(int spread) {
    this.spread = spread;
  }

  public int getShotsFired() {
    return shotsFired;
  }

  public void setShotsFired(int shotsFired) {
    this.shotsFired = shotsFired;
  }

  public int getMagSize() {
    return magSize;
  }

  public void setMagSize(int magSize) {
    this.magSize = magSize;
  }

  public int getCurrentAmmo() {
    return currentAmmo;
  }

  public void setCurrentAmmo(int currentAmmo) {
    this.currentAmmo = currentAmmo;
  }

  public int getReloadTime() {
    return reloadTime;
  }

  public void setReloadTime(int reloadTime) {
    this.reloadTime = reloadTime;
  }

  public long getReloadStart() {
    return reloadStart;
  }

  public void setReloadStart(long reloadStart) {
    this.reloadStart = reloadStart;
  }
}
