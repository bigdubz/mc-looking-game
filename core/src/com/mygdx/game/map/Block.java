package com.mygdx.game.map;


import com.mygdx.game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {

    final int blockSize = Main.BLOCK_SIZE;
    public boolean projPassable;
    public BufferedImage image;
    public double X;
    public double Y;
    public boolean solid;

    public Block(double X, double Y, BufferedImage image, boolean collidable, boolean projPassable){
        this.X = X;
        this.Y = Y;
        this.image = image;
        this.solid = collidable;
        this.projPassable = projPassable;
    }
    public void draw(Graphics2D g){
        g.drawImage(image, (int) (X * blockSize), (int) (Y * blockSize), null);
    }
}
