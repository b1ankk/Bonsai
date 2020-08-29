package com.mos;

import com.mos.frame.components.MyDrawingPanel;
import com.mos.frame.forms.MainFrame;
import com.mos.tree.BranchGrowthConfig;
import com.mos.tree.Tree;
import com.mos.util.Point;

import javax.swing.*;
import java.awt.*;

import static com.mos.tree.BranchGrowthConfig.BranchSetting;


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
        drawButton.addActionListener(e -> startDrawingTree());
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
          drawingPanel.getWidth() / 2,
          drawingPanel.getHeight()
        );
        
        Tree tree = new Tree(treeStart, 0, 80);
        drawingPanel.setTree(tree);
    
        BranchGrowthConfig branchGrowthConfig = new BranchGrowthConfig(12);
        BranchSetting setting1 = new BranchSetting()
            .withAngleDifference(-Math.PI / 8)
            .withColor(Color.GREEN)
            .withLengthChange(0.85)
            .withThickness(3);
        
        BranchSetting setting2 = setting1
            .withAngleDifference(Math.PI / 6)
            .withLengthChange(0.8);
    
        branchGrowthConfig.addBranchSetting(setting1);
        branchGrowthConfig.addBranchSetting(setting2);
        
        tree.grow(branchGrowthConfig);
        drawingPanel.repaint();
    }
    
}
