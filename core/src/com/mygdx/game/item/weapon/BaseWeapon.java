package com.mygdx.game.item.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;
import com.mygdx.game.entity.player.BasePlayer;
import com.mygdx.game.entity.projectile.Bullet;
import com.mygdx.game.item.BaseItem;

public abstract class BaseWeapon extends BaseItem {

    BasePlayer holder;
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
        super(m, imagePath, itemName);
        this.holder = holder;
        this.setMinDamage(minDamage);
        this.setMaxDamage(maxDamage);
        this.setFireRate(fireRate);
        this.setProjectileSpeed(projectileSpeed);
        this.setSpread(spread);
        this.setShotsFired(shotsFired);
        this.setMagSize(magSize);
        this.setCurrentAmmo(currentAmmo);
        this.setReloadTime(reloadTime);
        this.resetFireStart();
    }

    public void shootProjectile() {
        if (
            !(this.getCurrentAmmo() <= 0 ||
                this.isReloading ||
                System.currentTimeMillis() - fireStart < this.getFireRate())
        ) {
            resetFireStart();
            for (int i = 0; i < getShotsFired(); i++) {
                new Bullet(
                    this.main,
                    holder,
                    getRotation(),
                    getSpread(),
                    getProjectileSpeed(), // removed implementation below because idw use trig, another solution is to:
                    // TODO somehow draw projectiles below the weapon
                    getX() + getWidth() * 0.5f,
                    // + getWidth() * 0.5f * (float) (1 + Math.cos(Math.toRadians(getRotation()))),
                    getY() + getHeight() * 0.5f,
                    // + getWidth() * 0.5f * (float) Math.sin(Math.toRadians(getRotation())),
                    getMinDamage()
                );
            }
            setCurrentAmmo(getCurrentAmmo() - 1);
        }
    }

    @Override
    public void act(float delta) {
        if (isSelected()) {
            if (!(getElapsedReloadTime() < reloadTime || !isReloading)) {
                reload();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isSelected()) {
            this.setRotation(
                    holder.getAngle(
                        main.SCREEN_HALF_WIDTH,
                        -main.SCREEN_HALF_HEIGHT,
                        Gdx.input.getX(),
                        -Gdx.input.getY()
                    )
                );
            sprite.setFlip(
                false,
                this.getRotation() > 90 || this.getRotation() < -90
            );
            sprite.setRotation(this.getRotation());
            setX(holder.getX() + holder.getWidth() * 0.5f - getWidth() * 0.5f);
            setY(holder.getY() + holder.getHeight() * 0.5f - getHeight());
            sprite.setPosition(getX(), getY());
            sprite.draw(batch);
        }
    }

    public void reload() {
        setCurrentAmmo(this.getMagSize());
        isReloading = false;
    }

    public void startReload() {
        if (isReloading) return;
        setReloadStart(System.currentTimeMillis());
        isReloading = true;
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

    boolean isSelected() {
        return this.holder.getSelectedItem() == this;
    }

    void resetFireStart() {
        this.fireStart = System.currentTimeMillis();
    }
}
