package com.mos.tree;

import com.mos.Drawable;
import com.mos.tree.settings.TreeSettings;
import com.mos.util.Point;

import java.awt.*;
import java.util.ArrayList;

public class TreeNode implements Drawable {
    private final ArrayList<TreeBranch> childBranches = new ArrayList<>(4);
    
    private final Point position;
    private final int level;
    
    
    public TreeNode(Point position, int level) {
        this.position = position;
        this.level = level;
    }
    
    public void addChildBranch(TreeBranch childBranch) {
        childBranches.add(childBranch);
    }
    
    public void growAll(TreeSettings config) {
        childBranches.forEach(branch -> branch.grow(config));
    }
    
    
    @Override
    public void draw(Graphics2D g2d) {
        childBranches.forEach(branch -> branch.draw(g2d));
    }
    
    public int getLevel() {
        return level;
    }
    
    public Point getPosition() {
        return position;
    }
}
