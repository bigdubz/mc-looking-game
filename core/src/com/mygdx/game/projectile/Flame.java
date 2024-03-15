package com.mygdx.game.projectile;

import game.GamePanel;
import game.output.ImageManager;
import map.Block;
import player.Player;
import player.buffs.Debuff;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Flame extends Projectile{

    double deceleration = 0.003;
    float opacity = 1f;

    public Flame(GamePanel game,
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
    }

    @Override
    public void draw(Graphics2D g) {
        boolean xCheck = X * blockSize > -100 && X * blockSize < GamePanel.SCREEN_WIDTH + 100;
        boolean yCheck = Y * blockSize > -100 && Y * blockSize < GamePanel.SCREEN_HEIGHT + 100;
        if (xCheck && yCheck) {
            int blockSize = GamePanel.BLOCK_SIZE;
            AffineTransform backup = g.getTransform();
            AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
            int wiw = image.getWidth() / 2;
            int wih = image.getHeight() / 2;
            g.setTransform(AffineTransform.getRotateInstance(angle, X * blockSize, Y * blockSize));
            tx.translate(0, -image.getHeight());
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g.drawImage(image, (int) (X * blockSize) - wiw, (int) (Y * blockSize) - wih, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.setTransform(backup);
        }
    }

    @Override
    public void update() {
        if (update) {
            checkCollisionWithPlayer();
            checkCollision();
            move();
            calculateXYSpeed();
            projDeathAnimation();
        }
    }

    @Override
    void projDeathAnimation() {
        if (dead && aTime >= 3) {
            aTime = 0;
            opacity -= 0.25f;
            if (opacity <= 0) {
                update = false;
            }
        }
        aTime++;
    }

    @Override
    protected void loadImages() {
        image = new BufferedImage[]
                {
                        ImageManager.flame1, ImageManager.flame2, ImageManager.flame3
                }
                [game.getRandomInt(0, 3)];
    }

    private void move() {
        X += xSpeed;
        Y += ySpeed;
        speed -= deceleration;
        if (speed < 0) {
            speed = 0;
            dead = true;
        }
    }

    @Override
    protected void checkCollision() {
        for (Block obj : game.map.getNearbyBlocks(X, Y)){
            if (obj.projPassable) continue;
            if ((X >= obj.X && X <= obj.X + 1) && (Y >= obj.Y && Y <= obj.Y + 1)) {
                dead = true;
                break;
            }
        }
    }

    @Override
    void checkCollisionWithPlayer() {
        for (Player player : game.allPlayers) {
            if (player != owner && player.getClass() != owner.getClass()) {
                double w = (double) player.down1.getWidth() / blockSize / 2;
                double h = (double) player.down1.getHeight() / blockSize / 2;
                double x = player.X;
                double y = player.Y;
                boolean xCheck = X >= x - w && X <= x + w;
                boolean yCheck = Y >= y - h && Y <= y + h;
                if (xCheck && yCheck && !dead && !player.dead()) {
                    inflictBurn(player);
                    player.takeDamage(damage);
                    dead = true;
                    break;
                }
            }
        }
    }

    void inflictBurn(Player player) {
        int burnDamage = 2;
        if (player.buffs.size() > 5) {
            player.buffs.remove(0);
        }
        new Debuff(game, player, owner, burnDamage, 0, 0, 350, "On Fire!").startEffect();
    }
}
