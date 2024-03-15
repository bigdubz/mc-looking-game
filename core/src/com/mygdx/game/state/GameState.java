package com.mygdx.game.state;

import com.mygdx.game.Main;
import com.mygdx.game.item.Item;
import com.mygdx.game.item.weapon.*;
import com.mygdx.game.player.NPC;
import com.mygdx.game.projectile.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameState {
    Main game;
    public Wave currentWave;
    int currentWaveIndex = 0;
    int maxWaveDuration;
    public LinkedList<Item> items = new LinkedList<>();
    public LinkedList<NPC> allNPCs = new LinkedList<>();
    public LinkedList<Projectile> gameProjectiles = new LinkedList<>();
    public Wave wave1;
    Wave wave2;
    Wave wave3;
    Wave wave4;
    Wave wave5;
    Wave wave6;
    Wave wave7;
    Wave wave8;
    Wave wave9;
    Wave wave10;
    Wave wave11;
    Wave wave12;
    Wave wave13;
    Wave wave14;
    Wave wave15;
    ArrayList<Wave> allWaves = new ArrayList<>();

    public GameState(Main game, int maxWaveDuration) {
        this.game = game;
        this.maxWaveDuration = maxWaveDuration * 1000;
        makeWaves();
        startWave(allWaves.get(currentWaveIndex));
    }

    public void draw(Graphics2D g) {
        for (Item item : items) item.draw(g, item.image, item.X, item.Y, item.imgSizeW, item.imgSizeH);
        for (NPC npc : allNPCs) npc.draw(g);
        for (Projectile proj : gameProjectiles) proj.draw(g);
    }

    void drawWaveText(Graphics2D g) {
        int x = (int) (Main.SCREEN_WIDTH / 2.0);
        int y = Main.SCREEN_HEIGHT - 880;
//        if (!game.sManager.paused) game.drawCenteredString(g, "Wave " + currentWave.waveNumber,
//                new Rectangle(x, y, 0, 0), game.font.deriveFont(125f));
    }


    public void drawUI(Graphics2D g) {
        if (currentWave.elapsedTime <= 8500) {
            drawWaveText(g);
        }
        int x = Main.SCREEN_WIDTH;
        int y = Main.SCREEN_HEIGHT;
//        game.drawCenteredString(g, "Alive: " + allNPCs.size(), new Rectangle(x - 320, y - 850, 0, 0), game.font);
    }

    public void startWave(Wave wave) {
        currentWave = wave;
        items.clear();
        ArrayList<NPC> newNPCs = new ArrayList<>();
        for (int i = 0; i < currentWave.npcCount; i++) {
            NPC npc = new NPC(game, this, wave.npcLevel);
            newNPCs.add(npc);
            allNPCs.add(npc);
        }
        game.allPlayers.addAll(newNPCs);
        wave.startTime = System.currentTimeMillis();
    }

    public void update() {
//        if (!game.sManager.paused && !game.sManager.victoryScreen && !game.sManager.mainMenu) {
//            updateNPCs();
//            updateItems();
//            updateGameProjectiles();
//            updateWave();
//        }
    }

    private void updateWave() {
        currentWave.elapsedTime = System.currentTimeMillis() - currentWave.startTime;
        if (currentWave.elapsedTime > maxWaveDuration || allNPCs.isEmpty()) {
            currentWaveIndex++;
            if (currentWaveIndex > allWaves.size() - 1) {
//                if (allNPCs.isEmpty()) game.sManager.setVictoryScreen();
//                return;
            }
            startWave(allWaves.get(currentWaveIndex));
        }
    }

    void updateItems() {
        LinkedList<Item> itemsToBeRemoved = new LinkedList<>();
        items.forEach(item -> {
            if (item.pickedUp) {
                itemsToBeRemoved.add(item);
            }
        });
        items.removeAll(itemsToBeRemoved);
    }

    void updateNPCs() {
        LinkedList<NPC> notUpdated = new LinkedList<>();
        allNPCs.forEach(npc -> {
            if (npc.update) {
                npc.update();
            }
            else notUpdated.add(npc);
        });
        allNPCs.removeAll(notUpdated);
        game.allPlayers.removeAll(notUpdated);
    }

    void updateGameProjectiles() {
        LinkedList<Projectile> deadProj = new LinkedList<>();
        for (Projectile proj : gameProjectiles) {
            proj.update();
            if (!proj.update) deadProj.add(proj);
        }
        gameProjectiles.removeAll(deadProj);
    }

    private void makeWaves() {
        wave1 = new Wave(1, new LinkedList<Class>() {
            {
                add(Glock.class);
                add(FlameThrower.class);
            }
        }, false);
        wave2 = new Wave(2, new LinkedList<Class>() {
            {
                add(Glock.class);
                add(FlameThrower.class);
            }
        }, false);
        wave3 = new Wave(3, new LinkedList<Class>() {
            {
                add(Glock.class);
                add(Submachine.class);
            }
        }, false);
        wave4 = new Wave(4, new LinkedList<Class>() {
            {
                add(Glock.class);
                add(Submachine.class);
            }
        }, false);
        wave5 = new Wave(5, new LinkedList<Class>() {
            {
                add(Submachine.class);
                add(Ak47.class);
            }
        }, false);
        wave6 = new Wave(6, new LinkedList<Class>() {
            {
                add(Submachine.class);
                add(Ak47.class);
            }
        }, false);
        wave7 = new Wave(7, new LinkedList<Class>() {
            {
                add(Submachine.class);
                add(Ak47.class);
                add(Shotgun.class);
            }
        }, true);
        wave8 = new Wave(8, new LinkedList<Class>() {
            {
                add(Submachine.class);
                add(Ak47.class);
                add(Shotgun.class);
            }
        }, true);
        wave9 = new Wave(9, new LinkedList<Class>() {
            {
                add(Ak47.class);
                add(Shotgun.class);
                add(AWP.class);
            }
        }, true);
        wave10 = new Wave(10, new LinkedList<Class>() {
            {
                add(Ak47.class);
                add(Shotgun.class);
                add(AWP.class);
            }
        }, true);
        wave11 = new Wave(11, new LinkedList<Class>() {
            {
                add(Ak47.class);
                add(Shotgun.class);
                add(AWP.class);
                add(SG553.class);
            }
        }, true);
        wave12 = new Wave(12, new LinkedList<Class>() {
            {
                add(Ak47.class);
                add(Shotgun.class);
                add(AWP.class);
                add(SG553.class);
            }
        }, true);
        wave13 = new Wave(13, new LinkedList<Class>() {
            {
                add(Shotgun.class);
                add(AWP.class);
                add(SG553.class);
                add(RocketLauncher.class);
            }
        }, true);
        wave14 = new Wave(14, new LinkedList<Class>() {
            {
                add(Shotgun.class);
                add(AWP.class);
                add(SG553.class);
                add(RocketLauncher.class);
            }
        }, true);
        wave15 = new Wave(15, new LinkedList<Class>() {
            {
                add(Shotgun.class);
                add(AWP.class);
                add(SG553.class);
                add(RocketLauncher.class);
            }
        }, false);
        allWaves.addAll(new ArrayList<Wave>() {
            {
                add(wave1);
                add(wave2);
                add(wave3);
                add(wave4);
                add(wave5);
                add(wave6);
                add(wave7);
                add(wave8);
                add(wave9);
                add(wave10);
                add(wave11);
                add(wave12);
                add(wave13);
                add(wave14);
                add(wave15);
            }
        });
    }
}
