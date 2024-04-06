package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.item.BaseItem;

public abstract class BaseWeapon extends BaseItem {

  public int minDamage;
  public int maxDamage;
  public int fireRate;
  public int projS;
  public int spread;
  public int shotsFired;
  public int magSize;
  public int currentAmmo;
  public int reloadTime;
  public long reloadStart;

  public BaseWeapon(
      Main m,
      String imagePath,
      int minDamage,
      int maxDamage,
      int fireRate,
      int projS,
      int spread,
      int shotsFired,
      int magSize,
      int currentAmmo,
      int reloadTime,
      String itemName) {
    super(m, imagePath, itemName);
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
    this.fireRate = fireRate;
    this.projS = projS;
    this.spread = spread;
    this.shotsFired = shotsFired;
    this.magSize = magSize;
    this.currentAmmo = currentAmmo;
    this.reloadTime = reloadTime;
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

  public int getProjS() {
    return projS;
  }

  public void setProjS(int projS) {
    this.projS = projS;
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

  public void setReloadStart(long reloadStart) {
    this.reloadStart = reloadStart;
  }
}
