package com.mygdx.game.map;

import com.mygdx.game.Main;
//import com.mygdx.game.game.output.ImageManager;
import com.mygdx.game.pathFinding.PathFinder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map {

    public BufferedImage A, c, W, n, t, y, u, i, T, F, a,
                        z, b, v, l;

    {
//        A = ImageManager.scaleImage("A", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        a = ImageManager.scaleImage("A1", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        c = ImageManager.scaleImage("c", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        t = ImageManager.scaleImage("cR", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        y = ImageManager.scaleImage("cL", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        u = ImageManager.scaleImage("cD", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        i = ImageManager.scaleImage("cU", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        W = ImageManager.scaleImage("W", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        T = ImageManager.scaleImage("T", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        F = ImageManager.scaleImage("F", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        n = ImageManager.scaleImage("cTR", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        z = ImageManager.scaleImage("cTL", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        b = ImageManager.scaleImage("cBR", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        v = ImageManager.scaleImage("cBL", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
//        l = ImageManager.scaleImage("A2", "Blocks", Main.BLOCK_SIZE+1, Main.BLOCK_SIZE+1);
    }
    public final BufferedImage[][] map = {
            {A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, z, u, u, u, u, u, n, c, c, c, c, c, c, T, T, T, T, T, T, T, F, F, c, c, c, c, c, c, c, c, c, c, z, u, u, u, u, u, n, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, A, A, A, A, A, A, A, A},
            {A, c, c, c, c, c, c, c, c, c, c, v, i, i, i, i, i, b, c, c, c, c, c, c, A, l, l, l, l, A, a, a, a, a, A, l, l, l, l, A, c, c, c, v, i, i, i, i, i, b, c, c, c, c, c, c, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, T, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, A, l, l, l, l, A, l, l, l, l, A, A, c, c, c, T, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, F, c, c, c, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, c, c, c, T, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, F, c, c, T, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, A},
            {A, c, c, c, c, F, F, F, F, F, F, F, F, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, A, l, l, l, l, A, l, l, l, l, A, A, F, F, F, F, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, F, F, F, F, F, F, F, F, F, F, F, T, c, c, c, c, c, c, c, A, l, l, l, l, A, a, a, a, a, A, l, l, l, l, A, F, F, F, F, F, F, F, F, T, c, c, c, c, c, c, c, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, F, F, F, T, T, T, F, F, T, c, c, c, c, c, c, c, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, F, F, F, T, T, T, F, F, T, c, c, c, c, c, c, c, A, A, A, A, A, A, A, A},
            {A, c, c, c, c, c, c, F, F, F, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, F, F, F, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, F, F, F, c, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, F, F, F, c, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, F, F, F, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, F, F, F, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, F, F, c, c, c, c, c, c, c, F, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, F, F, c, c, c, c, c, c, c, F, F, F, c, c, c, c, c, c, A, A, A, A, A, A, c, c, A},
            {A, c, c, c, c, F, F, c, c, c, c, c, c, c, c, F, F, F, c, c, c, A, A, A, A, A, A, A, A, A, A, F, F, c, c, c, c, F, F, c, c, c, c, c, c, c, c, F, F, F, c, c, c, c, A, A, l, l, l, l, A, c, c, A},
            {A, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, F, F, F, F, F, F, F, l, l, l, l, l, l, F, F, F, F, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, F, F, F, F, F, F, F, l, l, l, l, l, A, c, c, A},
            {A, c, c, c, u, F, F, u, c, c, c, c, a, c, c, c, c, F, F, F, F, F, F, l, l, l, l, l, l, F, F, F, F, c, c, c, u, F, F, u, c, c, c, c, a, c, c, c, c, F, F, F, F, F, F, l, l, l, l, l, A, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, c, c, c, c, c, A, A, l, l, l, l, A, A, c, F, F, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, c, c, c, c, c, A, A, l, l, l, l, A, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, a, A, A, A, a, c, c, c, c, c, c, c, c, A, A, l, l, A, A, c, c, F, F, c, c, T, W, W, W, W, T, c, a, A, A, A, a, c, c, c, c, c, c, c, c, A, A, l, l, A, A, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, c, c, c, c, c, c, c, A, l, l, A, c, c, c, F, F, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, c, c, c, c, c, c, c, A, F, F, A, c, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, c, c, a, c, c, c, c, c, c, c, c, c, c, c, A, F, F, A, c, c, c, F, F, c, c, T, W, W, W, W, T, c, c, c, a, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, T, T, T, T, T, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, F, F, c, c, T, T, T, T, T, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, A},
            {A, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, F, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, z, u, u, u, u, u, n, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, z, u, u, u, u, u, n, c, c, c, c, c, c, T, T, T, T, T, T, T, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, c, c, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, c, c, c, t, W, W, W, W, W, y, c, c, c, c, c, c, A, A, A, A, A, A, A, A},
            {A, c, c, c, c, c, c, c, c, c, c, v, i, i, i, i, i, b, c, c, c, c, c, c, A, l, l, l, l, A, a, a, a, a, A, l, l, l, l, A, c, c, c, v, i, i, i, i, i, b, c, c, c, c, c, c, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, T, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, A, l, l, l, l, A, l, l, l, l, A, A, c, c, c, T, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, c, c, c, c, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, F, F, F, c, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, c, c, c, T, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, l, F, F, F, T, T, F, F, F, F, F, F, F, F, F, F, F, l, l, l, l, l, l, l, A},
            {A, c, c, c, c, F, F, F, F, F, F, F, F, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, A, l, l, l, l, A, l, l, l, l, A, A, F, F, F, F, F, F, F, T, T, T, T, T, T, T, A, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, F, F, F, F, F, F, F, F, F, F, F, T, c, c, c, c, c, c, c, A, l, l, l, l, A, a, a, a, a, A, l, l, l, l, A, F, F, F, F, F, F, F, F, T, c, c, c, c, c, c, c, A, l, l, l, l, l, l, A},
            {A, c, c, c, c, c, c, c, F, F, F, T, T, T, F, F, T, c, c, c, c, c, c, c, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, F, F, F, T, T, T, F, F, T, c, c, c, c, c, c, c, A, A, A, A, A, A, A, A},
            {A, c, c, c, c, c, c, F, F, F, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, c, F, F, F, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, c, F, F, F, c, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, c, c, c, F, F, F, c, c, c, c, T, F, F, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, c, c, F, F, F, c, c, c, c, c, T, F, F, c, c, c, c, c, c, c, c, A, A, A, A, c, c, c, F, F, c, c, c, c, F, F, F, c, c, c, c, c, c, F, F, c, c, c, c, c, c, c, c, A, F, F, A, c, c, c, A},
            {A, c, c, c, c, F, F, c, c, c, c, c, c, T, F, F, F, c, c, c, c, c, c, A, A, l, l, A, A, c, c, F, F, c, c, c, c, F, F, c, c, c, c, c, c, c, F, F, F, c, c, c, c, c, c, A, A, l, l, A, c, c, c, A},
            {A, c, c, c, c, F, F, c, c, c, c, c, c, c, T, F, F, F, c, T, T, T, A, A, l, l, l, l, A, A, c, F, F, c, c, c, c, F, F, c, c, c, c, c, c, c, c, F, F, F, c, c, c, c, A, A, l, l, l, A, c, c, c, A},
            {A, c, c, c, c, F, F, c, c, c, c, c, c, c, c, T, F, F, F, F, F, F, l, l, l, l, l, l, l, l, F, F, F, c, c, c, c, F, F, c, c, c, c, c, c, c, c, c, F, F, F, F, F, F, F, l, l, l, l, A, c, c, c, A},
            {A, c, c, c, u, F, F, u, c, c, c, c, a, c, c, c, T, F, F, F, F, F, l, l, l, l, l, l, l, l, F, F, F, c, c, c, u, F, F, u, c, c, c, c, a, c, c, c, c, F, F, F, F, F, F, l, l, l, l, A, c, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, T, T, T, T, T, A, A, A, A, A, A, A, A, c, F, F, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, c, c, c, c, c, A, A, A, A, A, A, c, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, a, A, A, A, a, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, T, W, W, W, W, T, c, a, A, A, A, a, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, T, W, W, W, W, T, c, c, a, A, a, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, T, W, W, W, W, T, c, c, c, a, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, T, W, W, W, W, T, c, c, c, a, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, c, c, T, T, T, T, T, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, F, F, c, c, T, T, T, T, T, T, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, c, A},
            {A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A}
    };

    int blockSize = Main.BLOCK_SIZE;
    public ArrayList<Block> allBlocks = new ArrayList<>(4100);
    public double worldX;
    public double worldY;
    Main game;
    public PathFinder pFinder;

    public Map(Main game) {
        this.game = game;
        pFinder = new PathFinder(game);
        mapMap();
    }
    public void mapMap() {
        int y = 0;
        int x = 0;
        for (BufferedImage[] row: map) {
            for (BufferedImage image: row) {
                boolean solid = image == A || image == W || image == T;
                boolean projCanPass = image == W;
                Block obj = new Block(x, y, image, solid, projCanPass);
                allBlocks.add(obj);
                if (x == 0) {
                    worldX = allBlocks.get(x).X;
                    worldY = allBlocks.get(x).Y;
                }
                x++;
            }
            x = 0;
            y++;
        }
    }
    public void drawMap(Graphics2D g) {
        for (Block obj : allBlocks) {
            if (obj.X * blockSize > -blockSize && obj.X * blockSize < Main.SCREEN_WIDTH &&
                    obj.Y * blockSize > -blockSize && obj.Y * blockSize < Main.SCREEN_HEIGHT){
                obj.draw(g);
            }
        }
    }
    public void updateWorldMap(){
        worldX = allBlocks.get(0).X;
        worldY = allBlocks.get(0).Y;
    }
    public ArrayList<Block> getNearbyBlocks(double X, double Y){
        ArrayList<Block> nearbyBlocks = new ArrayList<>(9);
        int col = game.getCol(X);
        int row = game.getRow(Y);
        nearbyBlocks.add(game.getBlock(X, Y));
        if (col > 0 && row > 0) nearbyBlocks.add(getPos(row-1, col-1));
        if (col < 63 && row > 0) nearbyBlocks.add(getPos(row-1, col+1));
        if (col > 0 && row < 63) nearbyBlocks.add(getPos(row+1, col-1));
        if (col < 63 && row < 63) nearbyBlocks.add(getPos(row+1, col+1));
        if (col > 0) nearbyBlocks.add(getPos(row, col-1));
        if (col < 63) nearbyBlocks.add(getPos(row, col+1));
        if (row > 0) nearbyBlocks.add(getPos(row-1, col));
        if (row < 63) nearbyBlocks.add(getPos(row+1, col));

        nearbyBlocks.removeIf(obj -> !obj.solid);
        return nearbyBlocks;
    }
    public Block getPos(int row, int col){
        return allBlocks.get(row * map.length + col);
    }
}