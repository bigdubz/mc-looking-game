package com.mygdx.game.pathFinding;

import com.mygdx.game.Main;

import java.util.ArrayList;

public class PathFinder {

    Main game;
    public Node[][] node;
    public ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    public Node startNode;
    public Node goalNode;
    public Node currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(Main game){
        this.game = game;
        instantiateNodes();
    }
    public void instantiateNodes(){
        node = new Node[64][64];
        int col = 0;
        int row = 0;

        while(col < 64 && row < 64){
            node[row][col] = new Node(row, col);
            col++;
            if (col == 64){
                col = 0;
                row++;
            }
        }
    }
    public void resetNodes(){
        int col = 0;
        int row = 0;

        while(col < 64 && row < 64){
            node[row][col].checked = false;
            node[row][col].solid = false;
            node[row][col].open = false;
            col++;
            if (col == 64){
                col = 0;
                row++;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        resetNodes();
        startNode = node[startRow][startCol];
        currentNode = startNode;
        goalNode = node[goalRow][goalCol];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < 64 && row < 64){
            if (game.map.getPos(row, col).solid){
                node[row][col].solid = true;
            }
            getCost(node[row][col]);
            col++;
            if (col == 64){
                col = 0;
                row++;
            }
        }
    }
    public void getCost(Node node) {
        int dx = Math.abs(node.col - startNode.col);
        int dy = Math.abs(node.row - startNode.row);
        node.gCost = dx + dy;
        dx = Math.abs(node.col - goalNode.col);
        dy = Math.abs(node.row - goalNode.row);
        node.hCost = dx + dy;
        node.fCost = node.gCost + node.hCost;
    }
    public boolean search(){

        while (!goalReached && step < 4096){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.checked = true;
            openList.remove(currentNode);

            if (row - 1 >= 0) openNode(node[row-1][col]);
            if (col - 1 >= 0) openNode(node[row][col-1]);
            if (row + 1 <= 63) openNode(node[row+1][col]);
            if (col + 1 <= 63) openNode(node[row][col+1]);

            int bestNodeIndex = 0;
            int bestFCost = 4096;

            for (int i = 0; i < openList.size(); i++){

                if (openList.get(i).fCost < bestFCost){
                    bestFCost = openList.get(i).fCost;
                    bestNodeIndex = i;
                }
                else if (openList.get(i).fCost == bestFCost){
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
                currentNode = openList.get(bestNodeIndex);
                if (currentNode == goalNode) {
                    goalReached = true;
                    trackPath();
                }
            }
            step++;
        }
        return goalReached;
    }
    public void openNode(Node node){
        if (!node.open && !node.solid && !node.checked){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
    private void trackPath(){
        Node current = goalNode;
        while (current != startNode){
            pathList.add(0, current);
            current = current.parent;
            if (current != startNode){
                pathList.add(current);
            }
        }
    }
}
