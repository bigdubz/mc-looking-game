package com.mygdx.game.projectile;

import com.mygdx.game.Main;
import com.mygdx.game.player.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Projectile {

    int blockSize = Main.BLOCK_SIZE;
    double speed;
    public double X;
    public double Y;
    double angle;
    double angleTau;
    double xSpeed;
    double ySpeed;
    int damage;
    int spread;
    public boolean update = true;
    Player owner;
    Main game;
    BufferedImage image;
    BufferedImage[] deathAnimation;
    int aTime = 0;
    int currentIndex = 0;
    boolean dead = false;
//    SoundManager impactSound;
    boolean playedImpactSound = false;

    public Projectile(Main game,
                      Player owner,
                      double X,
                      double Y,
                      double angle,
                      double angleTau,
                      int damage,
                      int speed,
                      int spread){
        this.game = game;
        this.owner = owner;
        this.spread = game.getRandomInt(-spread, spread);
        this.angle = angle + (this.spread*Math.PI/180);
        this.angleTau = angleTau;
        this.damage = damage;
        this.speed = (double) speed / Main.BLOCK_SIZE;
        this.X = X + xSpeed*0.5;
        this.Y = Y + ySpeed*0.5;
    }
    public abstract void update();
    protected abstract void loadImages();

    public void draw(Graphics2D g){
        boolean xCheck = X*blockSize > -100 && X*blockSize < Main.SCREEN_WIDTH + 100;
        boolean yCheck = Y*blockSize > -100 && Y*blockSize < Main.SCREEN_HEIGHT + 100;
        if (xCheck && yCheck) {
            int blockSize = Main.BLOCK_SIZE;
            AffineTransform backup = g.getTransform();
            AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
            int wiw = image.getWidth() / 2;
            int wih = image.getHeight() / 2;
            g.setTransform(AffineTransform.getRotateInstance(angle, X * blockSize, Y * blockSize));
            tx.translate(0, -image.getHeight());
            g.drawImage(image, (int) (X * blockSize) - wiw, (int) (Y * blockSize) - wih, null);
            g.setTransform(backup);
        }
    }
    void projDeathAnimation(){
        if (dead && aTime >= 3) {
            image = deathAnimation[currentIndex];
            currentIndex++;
            aTime = 0;
            if (currentIndex > deathAnimation.length-1) {
                update = false;
            }
        }
        aTime++;
    }
    protected void calculateXYSpeed(){
        xSpeed = speed * Math.cos(angle);
        ySpeed = speed * Math.sin(angle);
    }
    protected abstract void checkCollision();
    protected void playDeathSound(int startVol){
        if (dead && !playedImpactSound) {
            int distanceToPlayer = (int) (Math.sqrt(Math.pow(X - game.player.X,2) + Math.pow(Y - game.player.Y, 2)));
//            impactSound.playSound(startVol - 2*distanceToPlayer);
            playedImpactSound = true;
        }
    }
    abstract void checkCollisionWithPlayer();
    protected void damageArmor(Player p){
        if (p.armorHead != null) {
            p.armorHead.durability -= 1;
        }
        if (p.armorChest != null) {
            p.armorChest.durability -= 1;
        }
        if (p.armorGlove != null) {
            p.armorGlove.durability -= 1;
        }
        if (p.armorBoot != null) {
            p.armorBoot.durability -= 1;
        }
    }
    protected void countKill(Player player, Player owner){
        if (player.dead()) {
            owner.killCount += 1;
            owner.gainXP();
        }
    }
}
