package com.mos.tree;

import com.mos.RecursiveDrawable;
import com.mos.tree.settings.TreeSettings;
import com.mos.util.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.Math.PI;

public class Tree implements RecursiveDrawable {
    private final TreeBranch root;
    private final List<Queue<TreeBranch>> branchesToDraw = new ArrayList<>(21);
    
    public Tree(Point rootBase, double rootAngle, double rootLength,
                int thickness, Color startColor) {
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
    public void drawRecursive(Graphics2D g2d) {
        root.drawRecursive(g2d);
    }
    
    public void draw(Graphics2D g2d, Runnable repaint, int delayMillis) {
        preprocessBranchesToDraw();
        
        if (delayMillis == 0)
            drawAll(g2d, repaint);
        else
            drawGradually(g2d, repaint, delayMillis);
    }
    
    public void drawAll(Graphics2D g2d, Runnable repaint) {
        branchesToDraw.forEach(
            level -> level.forEach(
                branch -> branch.draw(g2d)
            )
        );
        repaint.run();
    }
    
    public void drawGradually(Graphics2D g2d, Runnable repaint, int delayMillis) {
        for (Queue<TreeBranch> level : branchesToDraw) {
            final long start = System.currentTimeMillis();
            level.parallelStream().forEach(
                branch -> branch.draw(g2d)
            );
            repaint.run();
        
            try {
                long wait = delayMillis - (System.currentTimeMillis() - start);
                if (wait > 10)
                    Thread.sleep(wait);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    
    public void preprocessBranchesToDraw() {
        final int initialProcessSize = (int) Math.pow(2, 21);
        Queue<TreeBranch> branchesToProcess = new ArrayDeque<>(initialProcessSize);
        branchesToProcess.add(root);
        
        while (!branchesToProcess.isEmpty()) {
            TreeBranch branch = branchesToProcess.poll();
            
            final int level = branch.getLevel();
            while (branchesToDraw.size() <= level) {
                int initialSize = (int) Math.pow(2, level + 1) - 1;
                branchesToDraw.add(new ArrayDeque<>(initialSize));
            }
            branchesToDraw.get(level).add(branch);
        
            TreeNode node = branch.getChildNode();
            if (node != null) {
                branchesToProcess.addAll(
                    node.getChildBranchesUnmodifiableList()
                );
            }
        }
    }
    
}
