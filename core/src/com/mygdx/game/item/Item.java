package com.mygdx.game.item;

import com.mygdx.game.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public abstract class Item {

    protected Main game;
    int blockSize = Main.BLOCK_SIZE;
    public boolean pickedUp;
    public double X;
    public double Y;
    public BufferedImage image;
    public double[] point;
    public String itemName;
    public int imgSizeW;
    public int imgSizeH;

    public void draw(Graphics g, BufferedImage image, double X, double Y, int W, int H){
        boolean xCheck = X*blockSize > -50 && X*blockSize < Main.SCREEN_WIDTH;
        boolean yCheck = Y*blockSize > -50 && Y*blockSize < Main.SCREEN_HEIGHT;
        if (xCheck && yCheck) {
            g.drawImage(image, (int) (X * blockSize), (int) (Y * blockSize), W, H, null);
        }
    }
    public BufferedImage loadImages(String imageName){
        try {
            File f = new File(Main.dir + "/res/Items/"+imageName+".png");
            image = ImageIO.read(Objects.requireNonNull(f));
        } catch (IOException ignored){
        }
        return image;
    }
}
