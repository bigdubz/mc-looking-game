package com.mygdx.game.item.weapon;

import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;
import com.mygdx.game.entity.projectile.Bullet;

public class Pistol extends BaseWeapon {
  public Pistol(Main m, BasePlayer holder) {
    super(m,
            "Items/Pistol.png",
            holder,
            5,
            10,
            250,
            25 * 25,
            3,
            1,
            9,
            9,
            1000,
            "Glock");
  }

  @Override
  public void shootProjectile(float angle) {
    if (this.getCurrentAmmo() > 0 && !this.isReloading) {
      new Bullet(
          this.main,
          holder,
          angle,
          getProjectileSpeed(),
          holder.getX(),
          holder.getY(),
          getMinDamage());
      setCurrentAmmo(getCurrentAmmo() - 1);
    }
  }
}
