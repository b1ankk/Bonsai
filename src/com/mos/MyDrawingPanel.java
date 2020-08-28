package com.mos;

import com.mos.tree.Tree;

import javax.swing.*;
import java.awt.*;

public class MyDrawingPanel extends JPanel {
    
    private Tree tree;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (tree != null)
            tree.draw((Graphics2D)g);
        
    }
    
    public Tree getTree() {
        return tree;
    }
    
    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
