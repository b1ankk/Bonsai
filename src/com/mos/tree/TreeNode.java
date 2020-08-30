package com.mos.tree;

import com.mos.Drawable;
import com.mos.tree.settings.TreeSettings;

import java.awt.*;
import java.util.ArrayList;

public class TreeNode implements Drawable {
    private final TreeBranch parentBranch;
    private final ArrayList<TreeBranch> childBranches = new ArrayList<>();
    
    private final int level;
    
    public TreeNode(TreeBranch parentBranch, int level) {
        this.parentBranch = parentBranch;
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
}
