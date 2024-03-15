package com.mygdx.game.player;

import com.mygdx.game.Main;
import com.mygdx.game.item.Item;
import com.mygdx.game.item.armor.ItemArmor;
import com.mygdx.game.item.weapon.FlameThrower;
import com.mygdx.game.item.weapon.Glock;
import com.mygdx.game.item.weapon.ItemWeapon;
import com.mygdx.game.map.Block;
import com.mygdx.game.rayCasting.RayCaster;

import java.awt.*;
import java.util.LinkedList;

public class Human extends Player {

    int regenCooldown = 5000; // ms
    double HPRegen = 0.1; // per frame
    public double startingPosX;
    public double startingPosY;
    public double dayVision = 8;
    public double nightVision = 4;
    public double vision = 8;
    public final UI ui = new UI(this);
    double playerXP = 0;
    double levelXP = 5;
    int thisColumn;
    int thisRow;
//    SoundManager lvlUp = new SoundManager("levelUp");
//    SoundManager[] walkIndoor = new SoundManager[5];
//    SoundManager[] walkOutdoor = new SoundManager[5];
    long walkNoiseTime = System.currentTimeMillis();
    public long regenTime = System.currentTimeMillis();
    RayCaster rayCaster;
    double RCdx = 0;
    double RCdy = 0;

    public Human(Main game) {
        X = Main.SCREEN_WIDTH * 0.5 / blockSize;
        Y = Main.SCREEN_HEIGHT * 0.5 / blockSize;
        this.main = game;
        double[] point = game.getRandomEmptyPoint();
        this.startingPosX = (int) point[0] + 0.5;
        this.startingPosY = (int) point[1] + 0.5;
        for (Block block : game.map.allBlocks) {
            block.X += (double) Main.SCREEN_WIDTH / blockSize - (startingPosX +
                    (double) Main.SCREEN_WIDTH / blockSize / 2);
            block.Y += (double) Main.SCREEN_HEIGHT / blockSize - (startingPosY +
                    (double) Main.SCREEN_HEIGHT / blockSize / 2);
        }
        setup();
    }

    private void setup() {
        moveSpeed = 7.5 / blockSize;
        loadImages();
        hitBox = new Rectangle((int) (X * blockSize) - image.getWidth() / 2 + 5,
                (int) (Y * blockSize), image.getWidth() - 10, image.getHeight() / 2);
        ui.inventory.pickUpItem(new Glock(main));
        ui.inventory.pickUpItem(new FlameThrower(main));
        ui.inventory.selectItem(0);
        healthPool = maxHealthPool;
        projectiles = new LinkedList<>();
//        hurtSound = new SoundManager("hit2");
//        walkIndoor[0] = new SoundManager("walk/indoor/walkIndoor1");
//        walkIndoor[1] = new SoundManager("walk/indoor/walkIndoor2");
//        walkIndoor[2] = new SoundManager("walk/indoor/walkIndoor3");
//        walkIndoor[3] = new SoundManager("walk/indoor/walkIndoor4");
//        walkIndoor[4] = new SoundManager("walk/indoor/walkIndoor5");
//        walkOutdoor[0] = new SoundManager("walk/outdoor/walkOutdoor1");
//        walkOutdoor[1] = new SoundManager("walk/outdoor/walkOutdoor2");
//        walkOutdoor[2] = new SoundManager("walk/outdoor/walkOutdoor3");
//        walkOutdoor[3] = new SoundManager("walk/outdoor/walkOutdoor4");
//        walkOutdoor[4] = new SoundManager("walk/outdoor/walkOutdoor5");
        rayCaster = new RayCaster(this, 360, 0.2);
        setStats();
    }

    private void loadImages() {
//        double size = 1.7;
//        down1 = ImageManager.scaleImage("down1", "Player", 32*size, 48*size);
//        down2 = ImageManager.scaleImage("down2", "Player", 32*size, 48*size);
//        down3 = ImageManager.scaleImage("down3", "Player", 32*size, 49*size);
//        BufferedImage down4 = ImageManager.scaleImage("down4", "Player", 30*size, 48*size);
//        BufferedImage down5 = ImageManager.scaleImage("down5", "Player", 30*size, 49*size);
//        BufferedImage down6 = ImageManager.scaleImage("down6", "Player", 32*size, 48*size);
//        BufferedImage down7 = ImageManager.scaleImage("down7", "Player", 32*size, 49*size);
//        BufferedImage down8 = ImageManager.scaleImage("down8", "Player", 30*size, 48*size);
//        BufferedImage down9 = ImageManager.scaleImage("down9", "Player", 30*size, 49*size);
//        up1 = ImageManager.scaleImage("up1", "Player", 32*size, 47*size);
//        up2 = ImageManager.scaleImage("up2", "Player", 32*size, 46*size);
//        up3 = ImageManager.scaleImage("up3", "Player", 31*size, 47*size);
//        BufferedImage up4 = ImageManager.scaleImage("up4", "Player", 31*size, 48*size);
//        BufferedImage up5 = ImageManager.scaleImage("up5", "Player", 31*size, 47*size);
//        BufferedImage up6 = ImageManager.scaleImage("up6", "Player", 32*size, 46*size);
//        BufferedImage up7 = ImageManager.scaleImage("up7", "Player", 31*size, 47*size);
//        BufferedImage up8 = ImageManager.scaleImage("up8", "Player", 31*size, 48*size);
//        BufferedImage up9 = ImageManager.scaleImage("up9", "Player", 31*size, 47*size);
//        left1 = ImageManager.scaleImage("left1", "Player", 26*size, 49*size);
//        left2 = ImageManager.scaleImage("left2", "Player", 27*size, 48*size);
//        left3 = ImageManager.scaleImage("left3", "Player", 27*size, 47*size);
//        BufferedImage left4 = ImageManager.scaleImage("left4", "Player", 26*size, 48*size);
//        BufferedImage left5 = ImageManager.scaleImage("left5", "Player", 29*size, 49*size);
//        BufferedImage left6 = ImageManager.scaleImage("left6", "Player", 29*size, 48*size);
//        BufferedImage left7 = ImageManager.scaleImage("left7", "Player", 28*size, 48*size);
//        BufferedImage left8 = ImageManager.scaleImage("left8", "Player", 27*size, 49*size);
//        BufferedImage left9 = ImageManager.scaleImage("left9", "Player", 28*size, 49*size);
//        right1 = ImageManager.scaleImage("right1", "Player", 21*size, 49*size);
//        right2 = ImageManager.scaleImage("right2", "Player", 25*size, 48*size);
//        right3 = ImageManager.scaleImage("right3", "Player", 23*size, 47*size);
//        BufferedImage right4 = ImageManager.scaleImage("right4", "Player", 23*size, 48*size);
//        BufferedImage right5 = ImageManager.scaleImage("right5", "Player", 24*size, 49*size);
//        BufferedImage right6 = ImageManager.scaleImage("right6", "Player", 28*size, 48*size);
//        BufferedImage right7 = ImageManager.scaleImage("right7", "Player", 25*size, 48*size);
//        BufferedImage right8 = ImageManager.scaleImage("right8", "Player", 24*size, 49*size);
//        BufferedImage right9 = ImageManager.scaleImage("right9", "Player", 23*size, 49*size);
//        downAnimation = new BufferedImage[]{down1, down2, down3, down4, down5, down6, down7, down8, down9};
//        upAnimation = new BufferedImage[]{up1, up2, up3, up4, up5, up6, up7, up8, up9};
//        leftAnimation = new BufferedImage[]{left1, left2, left3, left4, left5, left6, left7, left8, left9};
//        rightAnimation = new BufferedImage[]{right1, right2, right3, right4, right5, right6, right7, right8, right9};
//        URAnimation = new BufferedImage[]{right1, right2, right3, right4, right5, right6, right7, right8, right9};
//        ULAnimation = new BufferedImage[]{left1, left2, left3, left4, left5, left6, left7, left8, left9};
//        DRAnimation = new BufferedImage[]{right1, right2, right3, right4, right5, right6, right7, right8, right9};
//        DLAnimation = new BufferedImage[]{left1, left2, left3, left4, left5, left6, left7, left8, left9};
//        image = down1;
//        currentAnimation = downAnimation;
    }

    public void draw(Graphics2D g) {
        if (alive) {
            projectiles.forEach(projectile -> {
                if (projectile.update) {
                    projectile.draw(g);
                }
            });

            g.drawImage(image, (int) ((X * blockSize) - (double) image.getWidth() / 2),
                    (int) ((Y * blockSize) - (double) image.getHeight() / 2), null);
            if (itemEquipped != null && itemEquipped instanceof ItemWeapon) {
                drawWeapon(g,
                        X, Y,
                        itemEquipped.image.getWidth(),
                        itemEquipped.image.getHeight(),
                        angle, angleTau,
                        flipped, weaponImage);
            }
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(5));
            rayCaster.rayCast(g, 0, RCdx, RCdy, vision);
        }
    }

    public void update() {
        alive = healthPool > 0;
        if (alive) {
            RCdx = 0;
            RCdy = 0;
            updateArmor();
            handleInput();
            regenerateHP();
            updateWorldPosition();
            updateBuffs();
        }
        updateProjectiles();
    }

    private void updateWorldPosition() {
        thisColumn = main.getCol(X);
        thisRow = main.getRow(Y);
    }

    private void handleInput() {
        aim();
//        if (main.keyInput.F) ui.inventory.interact();
//        if (main.keyInput.E && itemEquipped != null) ui.inventory.selectNextItem();
//        if (main.keyInput.Q && itemEquipped != null) ui.inventory.selectPreviousItem();
//        if (main.keyInput.Z && itemEquipped != null) dropItem(itemEquipped);
//        if (main.keyInput.R) reloadWeapon();
//        if (main.mInput.RMB && itemEquipped instanceof ItemArmor) ui.inventory.equipArmor();
//        if (main.mInput.RMB) main.mInput.RMB = false;
//        if (main.mInput.LMB) shootProjectile();
//        if (main.keyInput.A || main.keyInput.S || main.keyInput.W || main.keyInput.D) move();
//        else image = currentAnimation[0];
    }

    private void shootProjectile() {
        if (itemEquipped instanceof ItemWeapon) {
            ItemWeapon weapon = (ItemWeapon) itemEquipped;
            long now = System.currentTimeMillis();
            boolean ammoCheck = weapon.currentAmmo > 0;
            boolean reloadTimeCheck = System.currentTimeMillis() - weapon.reloadStart >= weapon.reloadTime;
            if (now - shotCooldown >= weapon.fireRate && ammoCheck && reloadTimeCheck) {
                weapon.currentAmmo -= 1;
//                weapon.shoot.playSound(90);
                for (int i = 0; i < weapon.shotsFired; i++) {
                    int damage = main.getRandomInt(weapon.minDamage, weapon.maxDamage) + this.damage;
                    weapon.shootProjectile(projectiles,
                            main,
                            this,
                            X,
                            Y + (3.0 / blockSize),
                            angle,
                            angleTau,
                            damage);
                }
                shotCooldown = now;
            }
        }
    }

    private void reloadWeapon() {
//        main.keyInput.R = false;
//        if (itemEquipped instanceof ItemWeapon weapon && weapon.currentAmmo < weapon.magSize) {
//            weapon.reload.playSound(100);
//            weapon.currentAmmo = weapon.magSize;
//            weapon.reloadStart = System.currentTimeMillis();
//        }
    }

    private void aim() {
        double deltaX = Main.mouseX - (X*blockSize);
        double deltaY = Main.mouseY - (Y*blockSize);
        double acute = Math.atan(Math.abs(deltaY / deltaX));

        if (deltaX > 0 && deltaY > 0) {
            angle = acute;
            angleTau = acute;
        }
        if (deltaX < 0 && deltaY > 0) {
            angle = Math.PI - acute;
            angleTau = Math.PI - acute;
        }
        if (deltaX < 0 && deltaY < 0) {
            angle = Math.PI + acute;
            angleTau = Math.PI + acute;
        }
        if (deltaX > 0 && deltaY < 0) {
            angle = 2 * Math.PI - acute;
            angleTau = 2 * Math.PI - acute;
        }
    }

    private void move() {
        double speedX = 0;
        double speedY = 0;
//        if (main.keyInput.W) speedY -= moveSpeed;
//        if (main.keyInput.S) speedY += moveSpeed;
//        if (main.keyInput.D) speedX += moveSpeed;
//        if (main.keyInput.A) speedX -= moveSpeed;
//        if (speedX != 0 && speedY != 0) {
//            speedX /= Math.sqrt(2);
//            speedY /= Math.sqrt(2);
//        }
//        checkCollision(speedX, speedY);
//        if (speedX != 0 || speedY != 0) animate(speedX, speedY);
//        else image = currentAnimation[0];
    }

    private void animate(double dx, double dy) {
        if (dx > 0 && dy < 0) currentAnimation = URAnimation;
        else if (dx < 0 && dy < 0) currentAnimation = ULAnimation;
        else if (dx > 0 && dy > 0) currentAnimation = DRAnimation;
        else if (dx < 0 && dy > 0) currentAnimation = DLAnimation;
        else if (dy > 0) currentAnimation = downAnimation;
        else if (dy < 0) currentAnimation = upAnimation;
        else if (dx < 0) currentAnimation = leftAnimation;
        else if (dx > 0) currentAnimation = rightAnimation;
        if (animTime > 5) {
            if (currentIndex > currentAnimation.length - 1) currentIndex = 0;
            image = currentAnimation[currentIndex];
            animTime = 0;
            currentIndex++;
        }
        animTime++;
    }

    private void checkCollision(double dx, double dy) {
        boolean obstructedX = false;
        boolean obstructedY = false;

        // assign hit-box point coordinates for comparison
        // TOP RIGHT
        double x1 = (double) ((int) (X * blockSize - image.getWidth() / 2 + 5) + hitBox.width) / blockSize;
        double y1 = (double) (int) (Y * blockSize) / blockSize;

        // BOTTOM RIGHT
        double x2 = (double) ((int) (X * blockSize - image.getWidth() / 2 + 5) + hitBox.width) / blockSize;
        double y2 = (double) ((int) (Y * blockSize) + hitBox.height) / blockSize;

        // TOP LEFT
        double x3 = (double) (int) (X * blockSize - image.getWidth() / 2 + 5) / blockSize;
        double y3 = (double) (int) (Y * blockSize) / blockSize;

        // BOTTOM LEFT
        double x4 = (double) (int) (X * blockSize - image.getWidth() / 2 + 5) / blockSize;
        double y4 = (double) ((int) (Y * blockSize) + hitBox.height) / blockSize;

        for (Block obj : main.map.getNearbyBlocks(X, Y)) {
            // if either of the four points collide with a block, obstruct axis movement
            if ((x1 >= obj.X - dx && x1 <= obj.X + 1 - dx && y1 >= obj.Y && y1 <= obj.Y + 1) ||
                    (x2 >= obj.X - dx && x2 <= obj.X + 1 - dx && y2 >= obj.Y && y2 <= obj.Y + 1) ||
                    (x3 >= obj.X - dx && x3 <= obj.X + 1 - dx && y3 >= obj.Y && y3 <= obj.Y + 1) ||
                    (x4 >= obj.X - dx && x4 <= obj.X + 1 - dx && y4 >= obj.Y && y4 <= obj.Y + 1)) {
                obstructedX = true;
            }
            if ((x1 >= obj.X && x1 <= obj.X + 1 && y1 >= obj.Y - dy && y1 <= obj.Y + 1 - dy) ||
                    (x2 >= obj.X && x2 <= obj.X + 1 && y2 >= obj.Y - dy && y2 <= obj.Y + 1 - dy) ||
                    (x3 >= obj.X && x3 <= obj.X + 1 && y3 >= obj.Y - dy && y3 <= obj.Y + 1 - dy) ||
                    (x4 >= obj.X && x4 <= obj.X + 1 && y4 >= obj.Y - dy && y4 <= obj.Y + 1 - dy)) {
                obstructedY = true;
            }
        }
        for (Player player : main.allPlayers) {
            double w = player.hitBox.width / 2.0 / blockSize;
            double h = player.hitBox.height / 2.0 / blockSize;
            double x = player.X;
            double y = player.Y;
            if (player != this && !player.dead()) {
                if ((x1 >= x - w - dx && x1 <= x + w - dx && y1 >= y - h && y1 <= y + h) ||
                        (x2 >= x - w - dx && x2 <= x + w - dx && y2 >= y - h && y2 <= y + h) ||
                        (x3 >= x - w - dx && x3 <= x + w - dx && y3 >= y - h && y3 <= y + h) ||
                        (x4 >= x - w - dx && x4 <= x + w - dx && y4 >= y - h && y4 <= y + h)) {
                    obstructedX = true;
                }
                if ((x1 >= x - w && x1 <= x + w && y1 >= y - h - dy && y1 <= y + h - dy) ||
                        (x2 >= x - w && x2 <= x + w && y2 >= y - h - dy && y2 <= y + h - dy) ||
                        (x3 >= x - w && x3 <= x + w && y3 >= y - h - dy && y3 <= y + h - dy) ||
                        (x4 >= x - w && x4 <= x + w && y4 >= y - h - dy && y4 <= y + h - dy)) {
                    obstructedY = true;
                }
            }
        }
        if (!obstructedX) RCdx = dx;
        if (!obstructedY) RCdy = dy;
        moveAllObjects(dx, dy, obstructedX, obstructedY);
    }

    private void moveAllObjects(double dx, double dy, boolean obstructedX, boolean obstructedY) {
        // move all objects, keeping player in the center
//        for (Block obj : main.map.allBlocks) {
//            if (!obstructedX) obj.X -= dx;
//            if (!obstructedY) obj.Y -= dy;
//        }
//        projectiles.forEach(proj -> {
//            if (!obstructedX) proj.X -= dx;
//            if (!obstructedY) proj.Y -= dy;
//        });
//        main.gameState.gameProjectiles.forEach(proj -> {
//            if (!obstructedX) proj.X -= dx;
//            if (!obstructedY) proj.Y -= dy;
//        });
//        main.gameState.allNPCs.forEach(npc -> {
//            npc.projectiles.forEach(proj -> {
//                if (!obstructedX) proj.X -= dx;
//                if (!obstructedY) proj.Y -= dy;
//            });
//            if (!obstructedX) npc.X -= dx;
//            if (!obstructedY) npc.Y -= dy;
//        });
//        main.gameState.items.forEach(item -> {
//            if (!obstructedX) item.X -= dx;
//            if (!obstructedY) item.Y -= dy;
//        });
//        if (System.currentTimeMillis() - walkNoiseTime > 450) {
//            walkNoiseTime = System.currentTimeMillis();
//            Block currentBlock = main.getBlock(X, Y);
//            if (dx != 0 || dy != 0) {
//                if (currentBlock.image == main.map.l || currentBlock.image == main.map.a) {
//                    walkIndoor[main.getRandomInt(0, 5)].playSound(75);
//                } else {
//                    walkOutdoor[main.getRandomInt(0, 5)].playSound(70);
//                }
//            }
//        }
    }

    private void regenerateHP() {
        if (System.currentTimeMillis() - regenTime >= regenCooldown && healthPool < maxHealthPool) {
            healthPool += HPRegen;
            if (healthPool > maxHealthPool) healthPool = maxHealthPool;
        }
    }

    public void gainXP() {
        playerXP += 5;
        if (playerXP >= levelXP) {
            playerXP = 0;
            level += 1;
            setStats();
            healthPool += 10;
//            lvlUp.playSound(85);
        }
    }

    @Override
    public void setStats() {
        HPRegen = 0.1 + 0.025 * (level - 1);
        moveSpeed = (7.5 + 0.1 * (level - 1)) / blockSize;
        damage = level;
        maxHealthPool = 100 + 10 * (level - 1);
        levelXP = 5 * level;
        nightVision = 4 + 0.1 * level;
    }

    @Override
    protected void updateArmor() {
        armor = level;
        if (armorHead != null) {
            armor += armorHead.armor;
            if (armorHead.durability < 1) armorHead = null;
        }
        if (armorChest != null) {
            armor += armorChest.armor;
            if (armorChest.durability < 1) armorChest = null;
        }
        if (armorGlove != null) {
            armor += armorGlove.armor;
            if (armorGlove.durability < 1) armorGlove = null;
        }
        if (armorBoot != null) {
            armor += armorBoot.armor;
            if (armorBoot.durability < 1) armorBoot = null;
        }
    }

    @Override
    public void takeDamage(int damage) {
        damage -= armor;
        if (damage < 0) damage = 0;
        healthPool -= damage;
        regenTime = System.currentTimeMillis();
        alive = healthPool > 0;
//        hurtSound.playSound(100);
    }

    @Override
    void deathAnimation() {}

    void dropItem(Item item) {
//        main.keyInput.Z = false;
        ui.inventory.dropItem(item);
    }
}
