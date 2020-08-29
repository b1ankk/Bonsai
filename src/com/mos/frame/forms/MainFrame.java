package com.mos.frame.forms;

import com.mos.frame.components.BranchSettingsTabbedPaneManager;
import com.mos.frame.components.MyDrawingPanel;

import javax.swing.*;

public class MainFrame {
    // FORM COMPONENTS
    // border layout panel
    private JPanel rootPanel;
    
    // border layout bottom
    private JButton drawButton;
    
    // border layout center
    private JPanel centerPanel;
    private JPanel drawingPanel;
    
    // border layout right
    private JScrollPane settingsScrollPane;
    private JPanel settingsPanel;
    private JSlider levelAmountSlider;
    private JLabel levelAmountLabel;
    private JLabel branchesCountLabel;
    private JSlider branchesCountSlider;
    private JPanel branchSettingsRootPanel;
    
    // NON COMPONENT FIELDS
    private BranchSettingsTabbedPaneManager configsPaneManager;
    
    private void createUIComponents() {
        drawingPanel = new MyDrawingPanel();
        configsPaneManager = new BranchSettingsTabbedPaneManager();
    }
    
    public MainFrame() {
        init();
        addListeners();
    }
    
    private void init() {
        levelAmountLabel.setText(
            levelAmountSlider.getValue() + ""
        );
    
        configsPaneManager.addConfigPaneTo(branchSettingsRootPanel);
        configsPaneManager.updateTabsCount(branchesCountSlider.getValue());
    }
    
    private void addListeners() {
        levelAmountSlider.addChangeListener(
            e -> levelAmountLabel.setText(
                levelAmountSlider.getValue() + ""
            )
        );
        
        branchesCountSlider.addChangeListener(
            e -> {
                final int branchesCount = branchesCountSlider.getValue();
                
                branchesCountLabel.setText(
                    branchesCount + ""
                );
                configsPaneManager.updateTabsCount(branchesCount);
            }
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
