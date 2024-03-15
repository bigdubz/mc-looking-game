package com.mygdx.game.player;


import com.mygdx.game.Main;
import com.mygdx.game.item.Item;
import com.mygdx.game.item.armor.*;
import com.mygdx.game.player.buffs.Buffs;
import com.mygdx.game.projectile.Projectile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Player {

    static final int blockSize = Main.BLOCK_SIZE;
    public double X;
    public double Y;
    public double moveSpeed;
    double xSpeed;
    double ySpeed;
    int damage;
    public int maxHealthPool = 100;
    public double healthPool;
    double angle = 0;
    long shotCooldown = System.currentTimeMillis();
    public boolean alive = true;
    public boolean update = true;
    public int killCount;
    public BufferedImage down1;
    BufferedImage down2;
    BufferedImage down3;
    BufferedImage down4;
    BufferedImage up1;
    BufferedImage up2;
    BufferedImage up3;
    BufferedImage up4;
    BufferedImage right1;
    BufferedImage right2;
    BufferedImage right3;
    BufferedImage right4;
    BufferedImage left1;
    BufferedImage left2;
    BufferedImage left3;
    BufferedImage left4;
    BufferedImage UR1;
    BufferedImage UR2;
    BufferedImage UR3;
    BufferedImage UL1;
    BufferedImage UL2;
    BufferedImage UL3;
    BufferedImage DR1;
    BufferedImage DR2;
    BufferedImage DR3;
    BufferedImage DL1;
    BufferedImage DL2;
    BufferedImage DL3;
    BufferedImage death1;
    BufferedImage death2;
    BufferedImage death3;
    BufferedImage death4;
    BufferedImage death5;
    BufferedImage death6;
    BufferedImage[] downAnimation, upAnimation, leftAnimation,
            rightAnimation, URAnimation, ULAnimation, DRAnimation,
            DLAnimation, deathAnimation;
    BufferedImage image;
    BufferedImage[] currentAnimation;
    int animTime = 5;
    int deathAnimTime = 0;
    int currentIndex = 0;
    int currentDeathIndex = 0;
    public Item itemEquipped;
    public boolean flipped = false;
    double angleTau;
    public final double itemPickUpRange = 0.5;
    public BufferedImage weaponImage;
    public Main main;
    public Headgear armorHead;
    public Chest armorChest;
    public Gloves armorGlove;
    public Boots armorBoot;
    public int armor;
    LinkedList<Projectile> projectiles;
    public LinkedList<Buffs> buffs = new LinkedList<>();
    Rectangle hitBox;
    public int level = 1;

    public void drawHealthBar(Graphics2D g,
                              double x,
                              double y,
                              double imgWidth,
                              double imgHeight,
                              double healthPool,
                              int maxHealthPool) {
        if (healthPool > 0 && healthPool < 100) {
            double X = x * blockSize - imgWidth / 2 - 5;
            double Y = y * blockSize - imgHeight / 2 - 15;
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.lightGray);
            g.fillRect((int) X, (int) Y, (int) imgWidth + 10, 10);
            g.setColor(Color.red);
            g.fillRect((int) X, (int) Y, (int) ((imgWidth + 10) / maxHealthPool * healthPool), 10);
            g.setColor(Color.darkGray);
            g.drawRect((int) X, (int) Y, (int) imgWidth + 10, 10);
        }
    }

    public void drawWeapon(Graphics g,
                           double X,
                           double Y,
                           int itemWidth,
                           int itemHeight,
                           double angle,
                           double angleTau,
                           boolean flipped,
                           BufferedImage weaponImage) {
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform backup = g2.getTransform();
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        g2.setTransform(AffineTransform.getRotateInstance(angle, X * blockSize, Y * blockSize + 20));
        tx.translate(0, -weaponImage.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        if (angleTau >= Math.PI / 2 && angleTau <= 3 * Math.PI / 2 && !flipped) {
            weaponImage = op.filter(weaponImage, null);
        }
        else if (!(angleTau > Math.PI / 2 && angleTau < 3 * Math.PI / 2) && flipped) {
            weaponImage = op.filter(weaponImage, null);
        }
        double centX = X - (double) itemWidth / 2 / blockSize;
        double centY = Y - (double) itemHeight / 2 / blockSize;
        itemEquipped.draw(g2, weaponImage, centX, centY + 20.0 / blockSize,
                itemEquipped.imgSizeW, itemEquipped.imgSizeH);
        g2.setTransform(backup);
    }

    public void updateProjectiles() {
        AtomicInteger updateCount = new AtomicInteger();
        projectiles.forEach(projectile -> {
            if (projectile.update) {
                projectile.update();
                updateCount.getAndIncrement();
            }
        });
        if (updateCount.get() == 0) projectiles.clear();
    }

    abstract void updateArmor();

    public abstract void takeDamage(int damage);

    public abstract void gainXP();

    abstract void deathAnimation();

    public boolean dead() {
        return healthPool <= 0;
    }

    void updateBuffs() {
        LinkedList<Buffs> notUpdated = new LinkedList<>();
        for (Buffs buff : buffs) {
            if (buff.update) {
                buff.takeEffect();
            }
            else notUpdated.add(buff);
        }
        buffs.removeAll(notUpdated);
    }

    public void setStats() {
        maxHealthPool = 100 + 10 * level;
        moveSpeed = (6 + 0.1 * level) / blockSize;
        damage = level;
        armor = level;
    }
}