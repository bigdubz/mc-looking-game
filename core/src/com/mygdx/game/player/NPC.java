package com.mygdx.game.player;

import com.mygdx.game.Main;
import com.mygdx.game.state.GameState;
import com.mygdx.game.item.Inventory;
import com.mygdx.game.item.weapon.*;
import com.mygdx.game.map.Block;
import com.mygdx.game.pathFinding.Node;
import com.mygdx.game.pathFinding.PathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class NPC extends Player {

    Inventory inventory = new Inventory(this);
    boolean onPath = false;
    PathFinder pFinder;
    GameState gState;

    public NPC(Main game, GameState gState, int level) {
        this.main = game;
        this.gState = gState;
        this.level = level;
        double[] point = game.getRandomEmptyPoint();
        X = point[0] + 0.5;
        Y = point[1] + 0.5;
        inventory.pickUpItem(getRandomWeapon());
        inventory.selectItem(0);
        loadImages();
        healthPool = maxHealthPool;
        projectiles = new LinkedList<>();
        hitBox = new Rectangle((int) (X * blockSize) - image.getWidth() / 2 + 5,
                (int) (Y * blockSize), image.getWidth() - 10, image.getHeight() / 2);
//        hurtSound = new SoundManager("hit");
        pFinder = game.map.pFinder;
        setStats();
    }

    private void loadImages() {
        int w = 11 * 3;
        int h = 14 * 3;
        double size = 3;
//        down1 = ImageManager.scaleImage("npcWalk1", "Player/Skeleton", w, h);
//        down2 = ImageManager.scaleImage("npcWalk2", "Player/Skeleton", w, h);
//        down3 = ImageManager.scaleImage("npcWalk3", "Player/Skeleton", w, h);
//        down4 = ImageManager.scaleImage("npcWalk4", "Player/Skeleton", w, h);
//        right1 = ImageManager.scaleImage("npcWalkR1", "Player/Skeleton", w, h);
//        right2 = ImageManager.scaleImage("npcWalkR2", "Player/Skeleton", w, h);
//        right3 = ImageManager.scaleImage("npcWalkR3", "Player/Skeleton", w, h);
//        right4 = ImageManager.scaleImage("npcWalkR4", "Player/Skeleton", w, h);
//        left1 = ImageManager.scaleImage("npcWalkL1", "Player/Skeleton", w, h);
//        left2 = ImageManager.scaleImage("npcWalkL2", "Player/Skeleton", w, h);
//        left3 = ImageManager.scaleImage("npcWalkL3", "Player/Skeleton", w, h);
//        left4 = ImageManager.scaleImage("npcWalkL4", "Player/Skeleton", w, h);
//        up1 = ImageManager.scaleImage("npcWalkU1", "Player/Skeleton", w, h);
//        up2 = ImageManager.scaleImage("npcWalkU2", "Player/Skeleton", w, h);
//        up3 = ImageManager.scaleImage("npcWalkU3", "Player/Skeleton", w, h);
//        up4 = ImageManager.scaleImage("npcWalkU4", "Player/Skeleton", w, h);
//        death1 = ImageManager.scaleImage("npcDeath1", "Player/Skeleton", 14*size, 15*size);
//        death2 = ImageManager.scaleImage("npcDeath2", "Player/Skeleton", 14*size, 15*size);
//        death3 = ImageManager.scaleImage("npcDeath3", "Player/Skeleton", 11*size, 16*size);
//        death4 = ImageManager.scaleImage("npcDeath4", "Player/Skeleton", 12*size, 13*size);
//        death5 = ImageManager.scaleImage("npcDeath5", "Player/Skeleton", 12*size, 8*size);
//        death6 = ImageManager.scaleImage("npcDeath6", "Player/Skeleton", 12*size, 7*size);
        upAnimation = new BufferedImage[]{up1, up2, up3, up4};
        downAnimation = new BufferedImage[]{down1, down2, down3, down4};
        leftAnimation = new BufferedImage[]{left1, left2, left3, left4};
        rightAnimation = new BufferedImage[]{right1, right2, right3, right4};
        deathAnimation = new BufferedImage[]{death1, death2, death3, death4, death5, death6};
        image = down1;
        currentAnimation = downAnimation;
    }

    private void animate() {
        double pi = Math.PI;
        if (angleTau >= 5.0 / 4 * pi && angleTau < 7.0 / 4 * pi) currentAnimation = downAnimation;
        else if (angleTau >= pi / 4 && angleTau < 3.0 / 4 * pi) currentAnimation = upAnimation;
        else if (angleTau >= 7.0 / 4 * pi && angleTau < 2 * pi || angleTau >= 0 && angleTau < pi / 4)
            currentAnimation = rightAnimation;
        else currentAnimation = leftAnimation;

        if (animTime > 7) {
            if (currentIndex > currentAnimation.length - 1) currentIndex = 0;
            image = currentAnimation[currentIndex];
            animTime = 0;
            currentIndex++;
        }
        animTime++;
    }

    public void draw(Graphics2D g) {
        boolean xCheck = X * blockSize > -75 && X * blockSize < Main.SCREEN_WIDTH + 75;
        boolean yCheck = Y * blockSize > -75 && Y * blockSize < Main.SCREEN_HEIGHT + 75;
        if (xCheck && yCheck) {
            if (alive) {
                drawWeapon(g,
                        X, Y,
                        itemEquipped.image.getWidth(),
                        itemEquipped.image.getHeight(),
                        angle, angleTau,
                        flipped, weaponImage);
            }
            double x = (X * blockSize) - (double) image.getWidth() / 2;
            double y = (Y * blockSize) - (double) image.getHeight() / 2;
            g.drawImage(image, (int) (x), (int) (y), null);
            drawHealthBar( g, X, Y, image.getWidth(), image.getHeight(), healthPool, maxHealthPool);
        }
        projectiles.forEach(projectile -> {
            if (projectile.update) {
                projectile.draw(g);
            }
        });
        int x = (int) (X*blockSize);
        int y = (int) (Y*blockSize);
        int w = (int) (image.getWidth()/2.0);
        int h = (int) (image.getHeight()/2.0);
        g.drawRect(x - w, y - h, 2*w, 2*h);
    }

    public void update() {
        alive = healthPool > 0;
        if (alive) {
            move();
            shootProjectile();
            pathFindToPlayer();
            updateBuffs();
        }
        else {
            deathAnimation();
        }
        updateProjectiles();
    }

    private void move() {
        xSpeed = moveSpeed * Math.cos(angle);
        ySpeed = moveSpeed * Math.sin(angle);
        if (onPath && !inRangeToStop()) {
            moveCheckCollision(xSpeed, ySpeed);
            animate();
        }
    }

    private void shootProjectile() {
        if (itemEquipped instanceof ItemWeapon) {
            ItemWeapon weapon = (ItemWeapon) itemEquipped;
            long now = System.currentTimeMillis();
            boolean ammoCheck = weapon.currentAmmo > 0;
            boolean reloadTimeCheck = System.currentTimeMillis() - weapon.reloadStart >= weapon.reloadTime;
            boolean fireRateCheck = now - shotCooldown >= weapon.fireRate;
            if (!ammoCheck) {
                weapon.currentAmmo = weapon.magSize;
                weapon.reloadStart = System.currentTimeMillis();
            }
            if (fireRateCheck && inRangeToShoot() && ammoCheck && reloadTimeCheck) {
                weapon.currentAmmo -= 1;
//                int distanceToPlayer = (int) ((Math.sqrt(Math.pow(X - (Main.SCREEN_WIDTH * 0.5 / blockSize), 2)
//                        + Math.pow(Y - (Main.SCREEN_HEIGHT * 0.5 / blockSize), 2))));
//                weapon.shoot.playSound(90 - 2 * distanceToPlayer);
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

    private void getPlayerAngle() {
        double dx = (main.player.X - X) * blockSize;
        double dy = (main.player.Y - Y) * blockSize;
        double aTan = Math.atan(Math.abs(dy / (dx + 1e-10)));
        double theta = -aTan;
        if (dx > 0 && dy < 0) {
            angle = theta;
            angleTau = aTan;
        }
        if (dx < 0 && dy < 0) {
            angle = Math.PI - theta;
            angleTau = Math.PI - aTan;
        }
        if (dx < 0 && dy > 0) {
            angle = Math.PI + theta;
            angleTau = Math.PI + aTan;
        }
        if (dx > 0 && dy > 0) {
            angle = 2 * Math.PI - theta;
            angleTau = 2 * Math.PI - aTan;
        }
    }

    private void pathFindToPlayer() {
        onPath = true;
        if (canSeeHuman()) {
            getPlayerAngle();
        }
        else {
            int goalCol = main.getCol(main.player.X);
            int goalRow = main.getRow(main.player.Y);
            searchPath(goalCol, goalRow);
        }
    }

    private void moveCheckCollision(double dx, double dy) {
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
            // if either of the four points collide, obstruct axis movement
            if ((x1 >= obj.X - dx && x1 <= obj.X + 1 - dx && y1 >= obj.Y && y1 <= obj.Y + 1) ||
                    (x2 >= obj.X - dx && x2 <= obj.X + 1 - dx && y2 >= obj.Y && y2 <= obj.Y + 1) ||
                    (x3 >= obj.X - dx && x3 <= obj.X + 1 - dx && y3 >= obj.Y && y3 <= obj.Y + 1) ||
                    (x4 >= obj.X - dx && x4 <= obj.X + 1 - dx && y4 >= obj.Y && y4 <= obj.Y + 1)){
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
        if (!obstructedX) X += dx;
        if (!obstructedY) Y += dy;
    }

    private boolean inRangeToShoot() {
        double range = 4;
        return (Math.sqrt(Math.pow(X - main.player.X, 2) + Math.pow(Y - main.player.Y, 2)) <= range) && canSeeHuman();
    }

    private boolean inRangeToStop() {
        double range = 2.5;
        return (Math.sqrt(Math.pow(X - main.player.X, 2) + Math.pow(Y - main.player.Y, 2)) <= range) && canSeeHuman();
    }

    private ItemWeapon getRandomWeapon() {
        LinkedList<ItemWeapon> weapons = new LinkedList<>();
        LinkedList<?> w = gState.currentWave.allowedWeapons;
        if (w.contains(Glock.class)) weapons.add(new Glock(main));
        if (w.contains(Submachine.class)) weapons.add(new Submachine(main));
        if (w.contains(Ak47.class)) weapons.add(new Ak47(main));
        if (w.contains(Shotgun.class)) weapons.add(new Shotgun(main));
        if (w.contains(AWP.class)) weapons.add(new AWP(main));
        if (w.contains(SG553.class)) weapons.add(new SG553(main));
        if (w.contains(RocketLauncher.class)) weapons.add(new RocketLauncher(main));
        if (w.contains(FlameThrower.class)) weapons.add(new FlameThrower(main));

        return weapons.get(main.getRandomInt(0, weapons.size()));
    }

    @Override
    protected void updateArmor() {
    }

    @Override
    public void takeDamage(int damage) {
        int dmg = damage - armor;
        healthPool -= dmg;
//        hurtSound.playSound(90);
    }

    @Override
    void deathAnimation() {
        if (deathAnimTime >= 9) {
            image = deathAnimation[currentDeathIndex];
            if (currentDeathIndex > 0) {
                Y += (double) (deathAnimation[currentDeathIndex - 1].getHeight()
                        - deathAnimation[currentDeathIndex].getHeight()) / blockSize;
            }
            currentDeathIndex++;
            deathAnimTime = 0;
            if (currentDeathIndex > deathAnimation.length - 1) {
                inventory.dropItem(itemEquipped);
                gState.gameProjectiles.addAll(projectiles);
                update = false;
            }
        }
        deathAnimTime++;
    }

    @Override
    public void gainXP() {
    }

    public boolean canSeeHuman() {

        double humanNpcDeltaX = main.player.X - X;
        double humanNpcDeltaY = main.player.Y - Y;
        double acuteAngleToHuman = Math.atan(Math.abs(humanNpcDeltaX / humanNpcDeltaY));
        double accuracy = 0.1; // accurate up to this many blocks (also the hypotenuse of the dx dy triangle)

        double dx = accuracy*Math.sin(acuteAngleToHuman);
        double dy = accuracy*Math.cos(acuteAngleToHuman);
        double currentX = X;
        double currentY = Y;

        // BOTTOM RIGHT QUADRANT
        // don't need to reverse anything as that is the positive quadrant

        // BOTTOM LEFT QUADRANT
        if (humanNpcDeltaX < 0 && humanNpcDeltaY > 0) {
            dx *= -1;
        }

        // TOP LEFT QUADRANT
        else if (humanNpcDeltaX < 0 && humanNpcDeltaY < 0) {
            dx *= -1;
            dy *= -1;
        }

        // TOP RIGHT QUADRANT
        else if (humanNpcDeltaX > 0 && humanNpcDeltaY < 0) {
            dy *= -1;
        }

        double distanceToPlayer = Math.sqrt(Math.pow(humanNpcDeltaX, 2) + Math.pow(humanNpcDeltaY, 2));
        double depth = Math.ceil(distanceToPlayer) / accuracy; // blocks
        Player H = main.player;
        double wid = main.player.hitBox.width / 2.0 / blockSize;
        double hei = main.player.hitBox.height / 2.0 / blockSize;

        // main loop that checks if a solid block is in the way between human and npc
        for (int i = 0; i < depth; i++) {

            // current coordinates being checked
            int currentColumn = main.getCol(currentX);
            int currentRow = main.getRow(currentY);

            // check if the current x and y hit player
            if (currentX >= H.X - wid && currentX <= H.X + wid && currentY >= H.Y - hei && currentY <= H.Y + hei) {
                return distanceToPlayer <= main.player.vision + 0.5;
            }
            // check if the current block is solid
            if (main.map.getPos(currentRow, currentColumn).solid) {
                return false;
            }
            // if last blocked checked is not solid, increment current x and y values to check if next block is solid
            currentX += dx;
            currentY += dy;
        }
        return distanceToPlayer <= main.player.vision + 0.5;
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = main.getCol(X);
        int startRow = main.getRow(Y);

        pFinder.setNodes(startCol, startRow, goalCol, goalRow);
        Block NPCBlock = main.getBlock(X, Y);
        Block goalBlock = main.map.getPos(goalRow, goalCol);
        onPath = NPCBlock != goalBlock;

        if (pFinder.search() && onPath) {
            Node currentNode = pFinder.pathList.get(0);
            Block currentBlock = main.map.getPos(currentNode.row, currentNode.col);
            double dx = (currentBlock.X + 0.5) * blockSize - X * blockSize;
            double dy = (currentBlock.Y + 0.5) * blockSize - Y * blockSize;
            double aTan = Math.atan(Math.abs(dy / (dx + 1e-10)));
            double theta = -aTan;
            if (dx > 0 && dy < 0) {
                angle = theta;
                angleTau = aTan;
            }
            if (dx < 0 && dy < 0) {
                angle = Math.PI - theta;
                angleTau = Math.PI - aTan;
            }
            if (dx < 0 && dy > 0) {
                angle = Math.PI + theta;
                angleTau = Math.PI + aTan;
            }
            if (dx > 0 && dy > 0) {
                angle = 2 * Math.PI - theta;
                angleTau = 2 * Math.PI - aTan;
            }
        }
    }
}
