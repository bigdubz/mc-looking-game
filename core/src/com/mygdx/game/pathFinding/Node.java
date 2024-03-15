package com.mygdx.game.pathFinding;

public class Node {

    Node parent;
    public int col;
    public int row;
    public int gCost;
    public int hCost;
    public int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int row, int col){
        this.col = col;
        this.row = row;
    }
}
