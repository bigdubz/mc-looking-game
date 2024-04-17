package com.mygdx.game.entity.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Main;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.item.BaseItem;
import com.mygdx.game.item.inventory.Inventory;
import com.mygdx.game.item.weapon.BaseWeapon;

public abstract class BasePlayer extends Entity {

    private int healthPoints = 100;
    protected final Inventory inventory;
    protected BaseItem selectedItem;
    protected int speed;

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
            removeActor();
        }
    }

    protected void shootProjectile() {
        BaseWeapon weapon = getWeapon();
        if (weapon != null) {
            weapon.shootProjectile();
        }
    }

    public abstract void drawHealthBar(ShapeRenderer sr);

    @Override
    public void removeActor() {
        inventory.clearInventory();
        selectedItem.itemDropped();
        super.removeActor();
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

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public BaseItem getSelectedItem() {
        return this.selectedItem;
    }

    public Array<BaseItem> getItems() {
        return inventory.getItems();
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
        if (selectedItem != null) {
            selectedItem.setRotation(degrees);
        }
    }
}
