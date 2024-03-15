package com.mygdx.game.rayCasting;

import com.mygdx.game.Main;
import com.mygdx.game.map.Block;
import com.mygdx.game.player.Player;

import java.awt.*;

public class RayCaster {


    Player owner;
    double FOV;
    double halfFov;
    double angleDelta;
    int numRays;

    public RayCaster(Player owner, int FOV, double angleDelta){
        this.owner = owner;
        this.FOV = FOV * Math.PI / 180;
        halfFov = this.FOV/2;
        this.angleDelta = angleDelta * Math.PI / 180;
        this.numRays = (int) (FOV / angleDelta);
    }

    public void rayCast(Graphics2D g, double startAngle, double dx, double dy, double maxDepth){
        double currentAngle = startAngle - halfFov;
        double startX = owner.X;
        double startY = owner.Y;
        Block currentBlock = owner.main.getBlock(startX, startY);

        for (int i = 0; i < numRays; i++) {

            int blockSize = Main.BLOCK_SIZE;
            double depth;
            double xVert;
            double yVert;
            double dxVert;
            double dyVert;
            double deltaVert;
            double depthVert;
            double xHorz;
            double yHorz;
            double dxHorz;
            double dyHorz;
            double deltaHorz;
            double depthHorz;
            double sinA = Math.sin(currentAngle);
            double cosA = Math.cos(currentAngle);
            double diffX;
            double diffY;


            if (cosA >= 0) diffX = 1e-10;
            else diffX = -1e-10;
            if (sinA >= 0) diffY = 1e-10;
            else diffY = -1e-10;

            // Vertical
            if (cosA >= 0) {
                xVert = currentBlock.X + 1;
                dxVert = 1;
            } else {
                xVert = currentBlock.X;
                dxVert = -1;
            }
            depthVert = (xVert - startX) / (cosA + 1e-10);
            yVert = startY + depthVert * sinA;

            deltaVert = dxVert / (cosA + 1e-10);
            dyVert = sinA * deltaVert;


            for (int v = 0; v < (int) maxDepth; v++) {
                double x = xVert + diffX + dx;
                double y = yVert + diffY + dy;
                boolean inBounds = owner.main.getCol(x) > 0 && owner.main.getCol(x) < 64 &&
                        owner.main.getRow(y) > 0 && owner.main.getRow(y) < 64;
                if (!inBounds || (owner.main.getBlock(x, y).solid && !owner.main.getBlock(x, y).projPassable)) break;
                depthVert += deltaVert;
                xVert += dxVert;
                yVert += dyVert;
            }

            // Horizontal
            if (sinA >= 0) {
                yHorz = currentBlock.Y + 1;
                dyHorz = 1;
            } else {
                yHorz = currentBlock.Y;
                dyHorz = -1;
            }
            depthHorz = (yHorz - startY) / (sinA + 1e-10);
            xHorz = startX + depthHorz * cosA;

            deltaHorz = dyHorz / (sinA + 1e-10);
            dxHorz = deltaHorz * cosA;

            for (int h = 0; h < (int) maxDepth; h++) {
                double x = xHorz + diffX + dx;
                double y = yHorz + diffY + dy;
                boolean inBounds = owner.main.getCol(x) > 0 && owner.main.getCol(x) < 64 &&
                        owner.main.getRow(y) > 0 && owner.main.getRow(y) < 64;
                if (!inBounds || (owner.main.getBlock(x, y).solid && !owner.main.getBlock(x, y).projPassable)) break;
                depthHorz += deltaHorz;
                xHorz += dxHorz;
                yHorz += dyHorz;
            }
            if (depthVert < depthHorz){
                if (depthVert < 0) depth = depthHorz;
                else depth = depthVert;
            }
            else {
                if (depthHorz < 0) depth = depthVert;
                else depth = depthHorz;
            }
            depth++;
            if (depth > maxDepth) {
                depth = maxDepth;
            }
            double x = startX*blockSize + cosA * depth * blockSize;
            double y = startY*blockSize + sinA * depth * blockSize;
            double x2 = x + cosA * 10 * blockSize;
            double y2 = y + sinA * 10 * blockSize;
            if ((x >= 0 && x <= Main.SCREEN_WIDTH) || (y >= 0 && y <= Main.SCREEN_HEIGHT)) {
                g.drawLine((int) x, (int) y, (int) x2, (int) y2);
            }
            currentAngle += angleDelta;
        }
    }
}
