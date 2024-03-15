package com.mygdx.game.player;

import com.mygdx.game.Main;
import com.mygdx.game.item.Inventory;
import com.mygdx.game.item.weapon.ItemWeapon;

import java.awt.*;
import java.util.Objects;

import static com.mygdx.game.player.Player.blockSize;

public class UI {

    Human owner;
    Inventory inventory;
    Color[] colors = new Color[5];
    float[] fractions = new float[5];
    RadialGradientPaint gPaint;

    public UI(Human owner) {
        this.owner = owner;
        inventory = new Inventory(owner);
    }

    public void draw(Graphics2D g) {
        if (owner.alive) {
            if (Objects.equals(owner.main.cycle.state, "night")) gradEffectNight(g);
            if (Objects.equals(owner.main.cycle.state, "day")) gradEffectDay(g);
//            if (owner.main.keyInput.V) inventory.draw(g);
            drawInfo(g);
            owner.main.gameState.drawUI(g);
            drawHealthBar(g);
//            if (owner.main.keyInput.T) drawStatSheet(g);
        }
    }

    private void gradEffectNight(Graphics2D g) {
        colors[0] = new Color(0, 0, 0, 0.1f);
        colors[1] = new Color(0, 0, 0, 0.30f);
        colors[2] = new Color(0, 0, 0, 0.55f);
        colors[3] = new Color(0, 0, 0, 0.75f);
        colors[4] = new Color(0, 0, 0, 1f);
        fractions[0] = 0.1f;
        fractions[1] = 0.3f;
        fractions[2] = 0.6f;
        fractions[3] = 0.8f;
        fractions[4] = 1f;
        gPaint = new RadialGradientPaint((int) (owner.X * blockSize), (int) (owner.Y * blockSize),
                (float) (owner.vision * blockSize), fractions, colors);
        g.setPaint(gPaint);
        g.fillOval((int) ((owner.X - owner.vision) * blockSize),
                (int) ((owner.Y - owner.vision) * blockSize),
                (int) (owner.vision * 2 * blockSize),
                (int) (owner.vision * 2 * blockSize));
    }

    private void gradEffectDay(Graphics2D g) {
        colors[0] = new Color(0, 0, 0, 0.1f);
        colors[1] = new Color(0, 0, 0, 0.20f);
        colors[2] = new Color(0, 0, 0, 0.45f);
        colors[3] = new Color(0, 0, 0, 0.65f);
        colors[4] = new Color(0, 0, 0, 1f);
        fractions[0] = 0.1f;
        fractions[1] = 0.3f;
        fractions[2] = 0.6f;
        fractions[3] = 0.8f;
        fractions[4] = 1f;
        gPaint = new RadialGradientPaint((int) (owner.X * blockSize), (int) (owner.Y * blockSize),
                (float) (owner.vision * blockSize), fractions, colors);
        g.setPaint(gPaint);
        g.fillOval((int) ((owner.X - owner.vision) * blockSize),
                (int) ((owner.Y - owner.vision) * blockSize),
                (int) (owner.vision * 2 * blockSize),
                (int) (owner.vision * 2 * blockSize));
    }

    private void drawInfo(Graphics2D g) {
        int x = Main.SCREEN_WIDTH;
        int y = Main.SCREEN_HEIGHT;
//        g.setFont(owner.main.font);
        g.setColor(Color.darkGray);
//        owner.main.drawCenteredString(g, String.format("Kills: %d", owner.killCount),
//                new Rectangle(x - 320, y - 950, 0, 0), owner.main.font);
//        if (owner.itemEquipped instanceof ItemWeapon weapon) {
//            owner.main.drawCenteredString(g, weapon.currentAmmo + " / " + weapon.magSize,
//                    new Rectangle(x - 320, y - 130, 0, 0), owner.main.font);
//            owner.main.drawCenteredString(g, weapon.itemName,
//                    new Rectangle(x - 320, y - 50, 0, 0), owner.main.font);
//        }
    }

    private void drawHealthBar(Graphics2D g) {
        if (owner.healthPool > 0) {
            double x = 50;
            double y = 40;
            g.setStroke(new BasicStroke(10));
            double colorIncr = 255.0 / owner.maxHealthPool;
            Color color = new Color(
                    (int) (255 / (1 + 0.01 * owner.healthPool)), (int) (colorIncr * owner.healthPool), 0);
            g.setColor(Color.lightGray);
            g.fillRect((int) x, (int) y, 600, 75);
            g.setColor(color);
            g.fillRect((int) x, (int) y, (int) (600.0 / owner.maxHealthPool * owner.healthPool), 75);
            g.setColor(Color.darkGray);
            g.drawRect((int) x, (int) y, 600, 75);
//            owner.main.drawCenteredString(g, String.format("%.0f / %s", owner.healthPool, owner.maxHealthPool),
//                    new Rectangle(50, 40, 600, 75), owner.main.font);
        }
    }

    private void drawStatSheet(Graphics2D g) {
        int x = 50;
        int y = Main.SCREEN_HEIGHT - 300;
//        Font font = owner.main.font.deriveFont(40f);
//        int width = 25 + g.getFontMetrics(font).stringWidth(stats()[0]);
//        g.setColor(new Color(238, 167, 83));
//        g.fillRect(x, y, width, 250);
//        g.setStroke(new BasicStroke(8));
//        g.setColor(Color.darkGray);
//        g.drawRect(x, y, width, 250);
//        g.setFont(font);
//        for (int i = 0; i < stats().length; i++) {
//            g.drawString(stats()[i], x + 10, y + 35 + 35 * i);
//        }
    }

    private String[] stats() {
        double blockSize = Main.BLOCK_SIZE;
        return new String[] {
                String.format("Level: %s, %s/%s XP", owner.level,
                        (int) owner.playerXP, (int) owner.levelXP),
                String.format("Move Speed: %.1f ", owner.moveSpeed * blockSize),
                String.format("HP Regen: %.2f", owner.HPRegen),
                String.format("Night Vision: %.1f ", owner.nightVision),
                String.format("Damage: %s", owner.damage),
                String.format("Armor: %s", owner.armor)
        };
    }
}
