package com.mos.frame.layouts;

import com.mos.MyDrawingPanel;

import javax.swing.*;

public class MainFrame {
    // FORM COMPONENTS
    private JPanel rootPanel;
    private JButton drawButton;
    private JScrollPane settingsScrollPane;
    private JSlider levelAmountSlider;
    private JLabel levelAmountLabel;
    private JPanel settingsPanel;
    private JPanel centerPanel;
    private JPanel drawingPanel;
    
    private void createUIComponents() {
        drawingPanel = new MyDrawingPanel();
    }
    
    public MainFrame() {
        init();
        
        levelAmountSlider.addChangeListener(
            e -> levelAmountLabel.setText(
                levelAmountSlider.getValue() + ""
            )
        );
    }
    
    private void init() {
        
        
        
        levelAmountLabel.setText(
            levelAmountSlider.getValue() + ""
        );
    }
    
    public JPanel getRootPanel() {
        return rootPanel;
    }
    
    public JPanel getDrawingPanel() {
        return drawingPanel;
    }
    
    public JButton getDrawButton() {
        return drawButton;
    }
    
}
