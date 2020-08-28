package com.mos.tree;

import com.mos.Drawable;
import com.mos.util.Point;

import java.awt.*;

import static java.lang.Math.PI;

public class Tree implements Drawable {
    private final TreeBranch root;
    
    public Tree(Point rootBase, double rootAngle, double rootLength) {
        this.root = new TreeBranch(
            new TreeNode(null, 0),
            rootBase,
            rootAngle + PI / 2,
            rootLength
        );
    }
    
    public void grow(BranchGrowthConfig config) {
        root.grow(config);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        Stroke stroke = new BasicStroke(2);
        g2d.setColor(Color.GREEN);
        g2d.setStroke(stroke);
        
        root.draw(g2d);
    }
}
