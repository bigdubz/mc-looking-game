package com.mygdx.game.projectile;

import game.GamePanel;
import game.output.ImageManager;
import game.output.SoundManager;
import map.Block;
import player.Player;

import java.awt.image.BufferedImage;

public class Rocket extends Projectile{

    double[] originalPoint;
    double[] initialPlayerPos;
    public Rocket(GamePanel game,
                  Player owner,
                  double X,
                  double Y,
                  double angle,
                  double angleTau,
                  int damage,
                  int speed,
                  int spread) {
        super(game,
                owner,
                X,
                Y,
                angle,
                angleTau,
                damage,
                speed,
                spread);
        loadImages();
        calculateXYSpeed();
        originalPoint = game.getMouseCoordinates();
        originalPoint[0] /= blockSize;
        originalPoint[1] /= blockSize;
        initialPlayerPos = new double[] {game.map.worldX, game.map.worldY};
        impactSound = new SoundManager("RocketExplosion");
    }

    @Override
    public void update() {
        if (update) {
            Y += ySpeed;
            X += xSpeed;
            checkCollision();
            checkImpactWithPoint();
            playDeathSound(100);
            checkCollisionWithPlayer();
            projDeathAnimation();
        }
    }
    @Override
    protected void loadImages() {
        image = ImageManager.rocketImage;
        BufferedImage rocketD1 = ImageManager.rocketD1;
        BufferedImage rocketD2 = ImageManager.rocketD2;
        BufferedImage rocketD3 = ImageManager.rocketD3;
        BufferedImage rocketD4 = ImageManager.rocketD4;
        BufferedImage rocketD5 = ImageManager.rocketD5;
        BufferedImage rocketD6 = ImageManager.rocketD6;
        BufferedImage rocketD7 = ImageManager.rocketD7;
        BufferedImage rocketD8 = ImageManager.rocketD8;
        BufferedImage rocketD9 = ImageManager.rocketD9;
        BufferedImage rocketD10 = ImageManager.rocketD10;
        BufferedImage rocketD11 = ImageManager.rocketD11;
        BufferedImage rocketD12 = ImageManager.rocketD12;
        deathAnimation = new BufferedImage[]{
                rocketD1, rocketD2, rocketD3, rocketD4,
                rocketD5, rocketD6, rocketD7, rocketD8,
                rocketD9, rocketD10, rocketD11, rocketD12
        };
    }
    @Override
    protected void checkCollisionWithPlayer(){
        for (Player player: game.allPlayers){
            double w = (double) player.down1.getWidth() /blockSize;
            double h = (double) player.down1.getHeight() /blockSize;
            double x = player.X;
            double y = player.Y;
            boolean xCheck1 = X >= x - w/2 && X <= x + w/2;
            boolean yCheck1 = Y >= y - h/2 && Y <= y + h/2;
            boolean xCheck2 = X + 5.0/blockSize >= x - w/2 && X + 5.0/blockSize <= x + w/2;
            boolean yCheck2 = Y + 5.0/blockSize >= y - h/2 && Y + 5.0/blockSize <= y + h/2;
            if ((xCheck1 || xCheck2) && (yCheck1 || yCheck2) && !dead){
                if (player != owner) {
                    xSpeed = 0;
                    ySpeed = 0;
                    dead = true;
                    dealAOEDamage();
                    break;
                }
            }
        }
    }
    @Override
    protected void checkCollision(){
        double x = X + (double) (image.getWidth()/2) / GamePanel.BLOCK_SIZE;
        double y = Y + (double) (image.getHeight()/2) / GamePanel.BLOCK_SIZE;
        for (Block obj: game.map.getNearbyBlocks(X, Y)){
            if (obj.projPassable) continue;
            if ((x > obj.X && x < obj.X + 1) && (y > obj.Y && y < obj.Y + 1) && !dead) {
                xSpeed = 0;
                ySpeed = 0;
                dead = true;
                dealAOEDamage();
                break;
            }
        }
    }
    private void checkImpactWithPoint(){
        if (collidedWithPoint() && !dead){
            dealAOEDamage();
            xSpeed = 0;
            ySpeed = 0;
            dead = true;
        }
    }
    private void dealAOEDamage(){
        double explosionRadius = 1;
        for (Player player: game.allPlayers){
            double deltaX = X - player.X;
            double deltaY = Y - player.Y;
            double distanceToPlayer = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
            if (distanceToPlayer <= explosionRadius && player.healthPool > 0) {
                player.takeDamage(damage);
                damageArmor(player);
                if (player != owner) countKill(player, owner);
            }
        }
    }
    private boolean collidedWithPoint(){
        double deltaX = (originalPoint[0] + (game.map.worldX - initialPlayerPos[0])) - X;
        double deltaY = (originalPoint[1] + (game.map.worldY - initialPlayerPos[1])) - Y;
        double distanceToPoint = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        return distanceToPoint <= 0.3;
    }
}
