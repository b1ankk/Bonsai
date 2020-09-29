package com.mos.tree;

import com.mos.Drawable;
import com.mos.tree.settings.TreeSettings;
import com.mos.util.Point;

import java.awt.*;

import static java.lang.Math.PI;

public class Tree implements Drawable {
    private final TreeBranch root;
    
    public Tree(Point rootBase, double rootAngle, double rootLength, int thickness, Color startColor) {
        this.root = new TreeBranch(
            new TreeNode(rootBase, 0),
            rootAngle + PI / 2,
            rootLength,
            thickness,
            startColor
        );
    }
    
    public void grow(TreeSettings config) {
        root.grow(config);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        root.draw(g2d);
    }
}
