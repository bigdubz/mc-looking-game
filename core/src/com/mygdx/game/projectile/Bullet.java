package com.mygdx.game.projectile;

import game.GamePanel;
import game.output.ImageManager;
import game.output.SoundManager;
import map.Block;
import player.Player;

import java.awt.image.BufferedImage;

public class Bullet extends Projectile{

    public Bullet(GamePanel game,
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
        impactSound = new SoundManager("bulletImpact/"+ game.getRandomInt(1, 5));
    }
    @Override
    protected void loadImages(){
        image = ImageManager.bulletImage;
        BufferedImage bulletD1 = ImageManager.bulletD1;
        BufferedImage bulletD2 = ImageManager.bulletD2;
        BufferedImage bulletD3 = ImageManager.bulletD3;
        BufferedImage bulletD4 = ImageManager.bulletD4;
        deathAnimation = new BufferedImage[]{bulletD1, bulletD2, bulletD3, bulletD4};

    }
    public void update() {
        if (update) {
            checkCollision();
            checkCollisionWithPlayer();
            Y += ySpeed;
            X += xSpeed;
            playDeathSound(90);
            projDeathAnimation();
        }
    }
    @Override
    protected void checkCollision(){
        for (Block obj: game.map.getNearbyBlocks(X, Y)){
            if (obj.projPassable) continue;
            if ((X >= obj.X && X <= obj.X + 1) && (Y >= obj.Y && Y <= obj.Y + 1)) {
                xSpeed = 0;
                ySpeed = 0;
                dead = true;
                break;
            }
        }
    }
    @Override
    protected void checkCollisionWithPlayer() {
        for (Player player: game.allPlayers) {
            if (player != owner && player.getClass() != owner.getClass()) {
                double w = (double) player.down1.getWidth() / blockSize / 2;
                double h = (double) player.down1.getHeight() / blockSize / 2;
                double x = player.X;
                double y = player.Y;
                boolean xCheck = X >= x - w && X <= x + w;
                boolean yCheck = Y >= y - h && Y <= y + h;
                if (xCheck && yCheck && !dead && !player.dead()) {
                    xSpeed = 0;
                    ySpeed = 0;
                    player.takeDamage(damage);
                    damageArmor(player);
                    countKill(player, owner);
                    dead = true;
                    break;
                }
            }
        }
    }
}
