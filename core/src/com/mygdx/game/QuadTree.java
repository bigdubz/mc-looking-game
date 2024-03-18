package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


public class QuadTree {

    private static final int MAX_OBJECTS_BY_NODE = 20;
    private static final int MAX_LEVEL = 6;
    private final int level;
    private final Array<Block> objects;
    private final Rectangle bounds;
    private final QuadTree[] nodes;

    public QuadTree(int level, Rectangle bounds) {
        this.level = level;
        this.bounds = bounds;
        objects = new Array<>();
        nodes = new QuadTree[4];
    }

    public void clear() {
        objects.clear();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    public void insert(Block block) {
        Rectangle rect = block.getRectangle();
        if (nodes[0] != null) {
            int index = getIndex(rect);
            if (index != -1) {
                nodes[index].insert(block);
                return;
            }
        }

        objects.add(block);
        if (objects.size > MAX_OBJECTS_BY_NODE && level < MAX_LEVEL) {
            if (nodes[0] == null) split();

            int i = 0;
            while(i < objects.size) {
                int index = getIndex(objects.get(i).getRectangle());
                if (index != -1) nodes[index].insert(objects.removeIndex(i));
                else i++;
            }
        }
    }

    public void retrieve(Array<Block> list, Rectangle area) {
        int index = getIndex(area);
        if (index != -1 & nodes[0] != null) nodes[index].retrieve(list, area);
        list.addAll(objects);
    }

    public void retrieveFast(Array<Block> list, Rectangle area) {
        int index = getIndex(area);
        if (index != -1 & nodes[0] != null) nodes[index].retrieveFast(list, area);

        //  This if(..) is configurable: only process elements in MAX_LEVEL and MAX_LEVEL-1
        if (level == MAX_LEVEL || level == MAX_LEVEL-1 || level == MAX_LEVEL-2) list.addAll(objects);

    }

    private void split() {
        float subWidth =  (bounds.getWidth() * 0.5f);
        float subHeight = (bounds.getHeight() * 0.5f);
        float x = bounds.getX();
        float y = bounds.getY();

        nodes[0] = new QuadTree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
        nodes[1] = new QuadTree(level+1, new Rectangle(x, y, subWidth, subHeight));
        nodes[2] = new QuadTree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
        nodes[3] = new QuadTree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));

    }

    private int getIndex(Rectangle pRect) {
        int index = -1;
        float verticalMidpoint = bounds.getX() + (bounds.getWidth() * 0.5f);
        float horizontalMidpoint = bounds.getY() + (bounds.getHeight() * 0.5f);

        boolean topQuadrant = pRect.getY() < horizontalMidpoint
                && pRect.getY() + pRect.getHeight() < horizontalMidpoint;
        boolean bottomQuadrant = pRect.getY() > horizontalMidpoint;

        if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
            if (topQuadrant) index = 1;
            else if (bottomQuadrant) index = 2;
        }
        else if (pRect.getX() > verticalMidpoint) {
            if (topQuadrant) index = 0;
            else if (bottomQuadrant) index = 3;
        }

        return index;
    }
}