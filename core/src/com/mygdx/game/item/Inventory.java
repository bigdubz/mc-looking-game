package com.mygdx.game.item;

import com.mygdx.game.Main;
import com.mygdx.game.item.armor.*;
import com.mygdx.game.item.weapon.ItemWeapon;
import com.mygdx.game.player.Player;

import java.awt.*;
import java.util.ArrayList;

public class Inventory {

    public ArrayList<Item> inventory = new ArrayList<>();
    Player owner;
    final int X = 50;
    final int Y = 150;
    final int itemsPerRow = 4;
    int capacity = 16;

    public Inventory(Player owner) {
        this.owner = owner;
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(50, 50, 50, 210));
        g2.fillRect(X, Y, 500, 400);
        g2.setStroke(new BasicStroke(8));
        g2.setColor(new Color(35, 35, 35));
        g2.drawRect(X, Y, 500, 400);
        g2.setStroke(new BasicStroke(3));
        drawItemsInInventory(g2);
        drawArmorSlots(g2);
        drawCapacity(g2);
    }
    private void drawArmorSlots(Graphics2D g){
        int width = 100;
        int height = 400;
        int x = Main.SCREEN_WIDTH - X - width;
        int y = Y;
        g.setColor(new Color(50, 50, 50, 210));
        g.fillRect(x, y, width, height);
        g.setStroke(new BasicStroke(8));
        g.setColor(new Color(35, 35, 35));
        g.drawRect(x, y, width, height);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.lightGray);
        if (owner.armorHead != null) {
            g.drawImage(owner.armorHead.image, x+15, y+10, null);
        }
        if (owner.armorChest != null) {
            g.drawImage(owner.armorChest.image, x+10, y+110, null);
        }
        if (owner.armorGlove != null) {
            g.drawImage(owner.armorGlove.image, x+10, y+210, null);
        }
        if (owner.armorBoot != null) {
            g.drawImage(owner.armorBoot.image, x+10, y+310, null);
        }
        for (int i = 0; i < 4; i++){
            g.drawRect(x+10, y+10+(i*100), 80, 80);
        }

    }
    private void drawCapacity(Graphics2D g){
        g.setFont(new Font("ThaleahFat", Font.PLAIN, 20));
        g.drawString(inventory.size() + " / " + capacity, 495, 535);
    }
    private void drawItemsInInventory(Graphics2D g) {
        int itemIndex;
        int width = 100;
        int height = 50;
        for (Item item: inventory) {
            itemIndex = inventory.indexOf(item);
            double x = X + 15 + Main.BLOCK_SIZE * itemIndex;
            double y = Y + 25 + Math.floor((double) itemIndex / itemsPerRow) * 100;
            if (itemIndex >= 4 && itemIndex < 8) x -= Main.BLOCK_SIZE * 4;
            else if (itemIndex >= 8 && itemIndex < 12) x -= Main.BLOCK_SIZE * 8;
            else if (itemIndex >= 12 && itemIndex < 16) x -= Main.BLOCK_SIZE * 12;
            else if (itemIndex >= 16 && itemIndex < 20) x -= Main.BLOCK_SIZE * 16;
            g.drawImage(item.image, (int) x + (width - item.imgSizeW)/2,
                    (int) y + (height - item.imgSizeH)/2,item.imgSizeW, item.imgSizeH, null);
        }
        if (!inventory.isEmpty()) drawSelectedItemBox(g, width);
    }

    private void drawSelectedItemBox(Graphics2D g, double width) {
        int itemSelected = inventory.indexOf(owner.itemEquipped);
        double x = X + 8 + Main.BLOCK_SIZE * itemSelected;
        double y = Y + 8 + Math.floor((double) itemSelected / itemsPerRow) * 100;
        if (itemSelected >= 4 && itemSelected < 8) x -= Main.BLOCK_SIZE * 4;
        else if (itemSelected >= 8 && itemSelected < 12) x -= Main.BLOCK_SIZE * 8;
        else if (itemSelected >= 12 && itemSelected < 16) x -= Main.BLOCK_SIZE * 12;
        else if (itemSelected >= 16 && itemSelected < 20) x -= Main.BLOCK_SIZE * 16;
        g.setStroke(new BasicStroke(8));
        g.setColor(Color.yellow);
        g.drawRect((int) x, (int) y, (int) width + 15, 77);
    }
    public void pickUpItem(Item item){
        if (inventory.size() < capacity) {
            inventory.add(item);
            item.pickedUp = true;
        }
    }
    public void selectItem(int index){
        owner.itemEquipped = inventory.get(index);
        owner.weaponImage = inventory.get(index).image;
//        owner.main.keyInput.rightArrow = false;
//        owner.main.keyInput.leftArrow = false;
        owner.flipped = false;
    }
    public void equipArmor(){
//        owner.main.mInput.RMB = false;
        if (owner.itemEquipped instanceof Headgear && owner.armorHead == null){
            owner.armorHead = (Headgear) owner.itemEquipped;
            inventory.remove(owner.itemEquipped);
        }
        if (owner.itemEquipped instanceof Chest && owner.armorChest == null){
            owner.armorChest = (Chest) owner.itemEquipped;
            inventory.remove(owner.itemEquipped);
        }
        if (owner.itemEquipped instanceof Gloves && owner.armorGlove == null){
            owner.armorGlove = (Gloves) owner.itemEquipped;
            inventory.remove(owner.itemEquipped);
        }
        if (owner.itemEquipped instanceof Boots && owner.armorBoot == null){
            owner.armorBoot = (Boots) owner.itemEquipped;
            inventory.remove(owner.itemEquipped);
        }
        selectPreviousItem();
    }
    public void selectNextItem(){
//        owner.main.keyInput.E = false;
        int index = inventory.indexOf(owner.itemEquipped);
        if (index + 1 > inventory.size() - 1) index = -1;
        selectItem(index + 1);
    }
    public void selectPreviousItem(){
//        owner.main.keyInput.Q = false;
        int index = inventory.indexOf(owner.itemEquipped);
        if (index - 1 < 0) index = inventory.size();
        selectItem(index - 1);
    }
    public void dropItem(Item item){
        inventory.remove(item);
        item.pickedUp = false;
        item.X = owner.X;
        item.Y = owner.Y;
        owner.main.gameState.items.add(item);
        if (inventory.isEmpty()) owner.itemEquipped = null;
        else selectPreviousItem();
    }
    public void interact() {
//        owner.main.keyInput.F = false;
        for (Item item : owner.main.gameState.items) {
            // if item dist less than or equals pick up range
            if (Math.sqrt(Math.pow(item.X - owner.X, 2) + Math.pow(item.Y - owner.Y, 2)) <= owner.itemPickUpRange) {
                pickUpItem(item);
                if (item instanceof ItemWeapon) {
                    owner.itemEquipped = item;
                    owner.weaponImage = item.image;
                    owner.flipped = false;
                }
                break;
            }
        }
    }
}
