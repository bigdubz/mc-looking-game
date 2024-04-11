package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.item.BaseItem;
import com.mygdx.game.item.inventory.Inventory;
import com.mygdx.game.item.weapon.BaseWeapon;

public abstract class BasePlayer extends Entity {

    private int healthPoints = 100;
    final Inventory inventory;
    BaseItem selectedItem;
    int speed;

    public BasePlayer(Main m, String imagePath, int speed) {
        super(m, imagePath);
        this.speed = speed;
        inventory = new Inventory(this.main, this);
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

    protected void shootProjectile() {
        BaseWeapon weapon = getWeapon();
        if (weapon != null) {
            weapon.shootProjectile();
        }
    }

    public void removeActor() {
        this.remove();
    }

    public boolean checkAlive() {
        return this.healthPoints > 0;
    }

    public void loseHealthPoints(int damage) {
        setHealthPoints(healthPoints - damage);
    }

    public void setHealthPoints(int hp) {
        this.healthPoints = Math.max(0, hp);
    }

    public BaseItem getSelectedItem() {
        return this.selectedItem;
    }

    public void setSelectedItem(BaseItem selectedItem) {
        this.selectedItem = selectedItem;
    }

    public BaseWeapon getWeapon() {
        // If (selected item is not null) and (selected item is instance of BaseWeapon)
        if (!(selectedItem == null || !(selectedItem instanceof BaseWeapon))) {
            return (BaseWeapon) selectedItem;
        }
        return null;
    }

    protected void setSelectedItemRotation(float degrees) {
        selectedItem.setRotation(degrees);
    }
}
