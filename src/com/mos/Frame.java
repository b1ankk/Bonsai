package com.mos;

import com.mos.frame.components.MyDrawingPanel;
import com.mos.frame.forms.MainFrame;
import com.mos.tree.Tree;
import com.mos.tree.settings.TreeSettings;
import com.mos.util.Point;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private MainFrame frameBase;
    private MyDrawingPanel drawingPanel;
    private JButton drawButton;
    
    public Frame() throws HeadlessException {
//        setUpLookAndFeel();
        init();
        setUpUi();
    }
    
    private void init() {
        setTitle("Bonsai");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = 800;
        int height = 600;
        int x = toolkit.getScreenSize().width / 2 - width / 2;
        int y = toolkit.getScreenSize().height / 2 - height / 2;
        
        setBounds(x, y, width, height);
        
        setVisible(true);
    }
    
    private void setUpUi() {
        frameBase = new MainFrame();
        setContentPane(frameBase.getRootPanel());
    
        drawingPanel = (MyDrawingPanel) frameBase.getDrawingPanel();
    
        drawButton = frameBase.getDrawButton();
        drawButton.addActionListener(
            e -> startDrawingTree()
        );
    }
    
    private void setUpLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        }
        catch (ClassNotFoundException | UnsupportedLookAndFeelException |
               IllegalAccessException | InstantiationException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    private void startDrawingTree() {
        Point treeStart = new Point(
          drawingPanel.getImageWidth() / 2,
          drawingPanel.getImageHeight() / 5 * 4
        );
    
        TreeSettings treeSettings = frameBase.buildTreeSettings();
        
        Tree tree = new Tree(treeStart, 0, treeSettings.getStartLength());
        drawingPanel.setTree(tree);
        
        tree.grow(treeSettings);
        drawingPanel.drawTree();
    }
    
}
