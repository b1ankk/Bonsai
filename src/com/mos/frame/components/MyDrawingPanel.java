package com.mos.frame.components;

import com.mos.tree.Tree;
import com.mos.tree.TreeImage;

import javax.swing.*;
import java.awt.*;

public class MyDrawingPanel extends JPanel {
    private TreeImage treeImage;
    private Tree tree;
    
    private Thread drawingThread;
    
    public MyDrawingPanel(int imageWidth, int imageHeight) {
        treeImage = TreeImage.getDefaultImage(
            imageWidth, imageHeight
        );
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        final int x = (getWidth() - treeImage.getWidth()) / 2;
        final int y = (getHeight() - treeImage.getHeight()) / 2;
        g.drawImage(treeImage, x, y, this);
    }
    
    public void resizeImage(int width, int height) {
        // TODO this method should copy settings from the old image
        
        TreeImage newImage = TreeImage.getDefaultImage(width, height);
        newImage.getBufferedGraphics2D()
                .drawImage(
                    treeImage, null,
                    (newImage.getWidth() - treeImage.getWidth()) / 2,
                    newImage.getHeight()
                );
        
        treeImage = newImage;
    }
    
    
    public void drawTree() {
        if (tree != null) {
            finishDrawingThreadIfNeeded();
            treeImage.clear();
            startDrawingTreeInDrawingThread(5);
        }
    }
    
    private void finishDrawingThreadIfNeeded() {
        if (drawingThread != null)
            try {
                drawingThread.interrupt();
                drawingThread.join();
                drawingThread = null;
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
    }
    
    private void startDrawingTreeInDrawingThread(int levelDrawDelayMillis) {
        drawingThread = new Thread(
            () -> tree.draw(
                treeImage.getBufferedGraphics2D(),
                this::repaint,
                levelDrawDelayMillis
            )
        );
        drawingThread.start();
    }
    
    
    public int getImageWidth() {
        return treeImage.getWidth();
    }
    
    public int getImageHeight() {
        return treeImage.getHeight();
    }
    
    public Tree getTree() {
        return tree;
    }
    
    public void setTree(Tree tree) {
        this.tree = tree;
    }
}
