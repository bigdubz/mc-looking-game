package com.mygdx.game.item.weapon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;
import com.mygdx.game.entity.projectile.Bullet;
import com.mygdx.game.item.BaseItem;

public abstract class BaseWeapon extends BaseItem {

    int minDamage;
    int maxDamage;
    int fireRate;
    long fireStart;
    int projectileSpeed;
    int spread;
    int shotsFired;
    int magSize;
    int currentAmmo;
    int reloadTime;
    long reloadStart;
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
        String itemName
    ) {
        super(m, holder, imagePath, itemName);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.fireRate = fireRate;
        this.projectileSpeed = projectileSpeed;
        this.spread = spread;
        this.shotsFired = shotsFired;
        this.magSize = magSize;
        this.currentAmmo = currentAmmo;
        this.reloadTime = reloadTime;
        resetFireStart();
    }

    public void shootProjectile() {
        if (
            !(currentAmmo <= 0 ||
                isReloading ||
                elapsedLastShotTime() < fireRate)
        ) {
            resetFireStart();
            for (int i = 0; i < shotsFired; i++) {
                new Bullet(
                    main,
                    holder,
                    getRotation(),
                    spread,
                    projectileSpeed, // removed implementation below because idw use trig, another solution is to:
                    // TODO somehow draw projectiles below the weapon
                    getX() + getWidth() * 0.5f,
                    // + getWidth() * 0.5f * (float) (1 + Math.cos(Math.toRadians(getRotation()))),
                    getY() + getHeight() * 0.5f,
                    // + getWidth() * 0.5f * (float) Math.sin(Math.toRadians(getRotation())),
                    minDamage,
                    maxDamage
                );
            }
            currentAmmo--;
        }
    }

    @Override
    public void act(float delta) {
        if (isSelected()) {
            if (!(elapsedReloadTime() < reloadTime || !isReloading)) {
                reload();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isSelected()) {
            sprite.setFlip(
                false,
                this.getRotation() > 90 || this.getRotation() < -90
            );
            sprite.setRotation(getRotation());
            setX(holder.getX() + holder.getWidth() * 0.5f - getWidth() * 0.5f);
            setY(holder.getY() + holder.getHeight() * 0.5f - getHeight());
            sprite.setPosition(getX(), getY());
            sprite.draw(batch);
        }
    }

    public void reload() {
        currentAmmo = magSize;
        isReloading = false;
    }

    public void startReload() {
        if (isReloading || currentAmmo == magSize) return;
        resetReloadStart();
        isReloading = true;
    }

    public long elapsedReloadTime() {
        return System.currentTimeMillis() - reloadStart;
    }

    public long elapsedLastShotTime() {
        return System.currentTimeMillis() - fireStart;
    }

    public void resetReloadStart() {
        this.reloadStart = System.currentTimeMillis();
    }

    void resetFireStart() {
        this.fireStart = System.currentTimeMillis();
    }

    boolean isSelected() {
        return this.holder.getSelectedItem() == this;
    }

    // TODO do something with the reload times to prevent reloading while weapon is not selected
    @Override
    public void itemDeselected() {}

    @Override
    public void itemSelected() {}
}
