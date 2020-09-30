package com.mos.tree;

import com.mos.RecursiveDrawable;
import com.mos.tree.settings.TreeSettings;
import com.mos.util.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNode implements RecursiveDrawable {
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
    public void drawRecursive(Graphics2D g2d) {
        childBranches.forEach(branch -> branch.drawRecursive(g2d));
    }
    
    public int getLevel() {
        return level;
    }
    
    public Point getPosition() {
        return position;
    }
    
    public List<TreeBranch> getChildBranchesUnmodifiableList() {
        return Collections.unmodifiableList(childBranches);
    }
}
