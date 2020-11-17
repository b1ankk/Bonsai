package com.mos.frame.forms;

import com.mos.frame.components.BranchSettingsTabbedPaneManager;
import com.mos.frame.components.MyDrawingPanel;
import com.mos.tree.settings.BranchSetting;
import com.mos.tree.settings.TreeSettings;

import javax.swing.*;
import java.math.BigInteger;
import java.util.List;

public class MainFrame {
    // FORM COMPONENTS
    // border layout panel
    private JPanel rootPanel;
    
    // border layout bottom
    private JButton drawButton;
    private JButton clearButton;
    
    // border layout center
    private JPanel centerPanel;
    private JPanel drawingPanel;
    
    // border layout right
    private JScrollPane settingsScrollPane;
    private JPanel settingsPanel;
    private JLabel linesToBeDrawnLabel;
    private JPanel branchSettingsRootPanel;
    
    private JSlider levelCountSlider;
    private JLabel levelCountLabel;
    
    private JSlider startLengthSlider;
    private JLabel startLengthLabel;
    
    private JLabel startThicknessLabel;
    private JSlider startThicknessSlider;
    
    private JSlider thicknessChangeSlider;
    private JLabel thicknessChangeLabel;

    private JLabel branchesCountLabel;
    private JSlider branchesCountSlider;
    
    
    // NON COMPONENT FIELDS
    private BranchSettingsTabbedPaneManager settingsPaneManager;
    
    private void createUIComponents() {
        drawingPanel = new MyDrawingPanel(1024, 1024);
        settingsPaneManager = new BranchSettingsTabbedPaneManager();
    }
    
    public MainFrame() {
        init();
        addListeners();
    }
    
    private void init() {
        settingsPaneManager.addConfigPaneTo(branchSettingsRootPanel);
        settingsPaneManager.updateTabsCount(branchesCountSlider.getValue());
        settingsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        updateUi();
    }
    
    private void updateUi() {
        updateLinesToBeDrawnLabel();
        updateLevelCountLabel();
        updateStartLengthLabel();
        updateStartThicknessLabel();
        updateThicknessChangeLabel();
        updateBranchesCountLabel();
    }
    
    private void addListeners() {
        clearButton.addActionListener(
            e -> ((MyDrawingPanel) drawingPanel).clear()
        );
        
        levelCountSlider.addChangeListener(
            e -> updateLevelCountLabel()
        );
        branchesCountSlider.addChangeListener(
            e -> {
                updateBranchesCountLabel();
                settingsPaneManager.updateTabsCount(
                    branchesCountSlider.getValue()
                );
            }
        );
        startLengthSlider.addChangeListener(
            e -> updateStartLengthLabel()
        );
        startThicknessSlider.addChangeListener(
            e -> updateStartThicknessLabel()
        );
        thicknessChangeSlider.addChangeListener(
            e -> updateThicknessChangeLabel()
        );
        
        levelCountSlider.addChangeListener(
            e -> updateLinesToBeDrawnLabel()
        );
        branchesCountSlider.addChangeListener(
            e -> updateLinesToBeDrawnLabel()
        );
    }
    
    
    private void updateLevelCountLabel() {
        levelCountLabel.setText(
            levelCountSlider.getValue() + ""
        );
    }
    
    private void updateBranchesCountLabel() {
        branchesCountLabel.setText(
            branchesCountSlider.getValue() + ""
        );
    }
    
    private void updateStartLengthLabel() {
        startLengthLabel.setText(
            startLengthSlider.getValue() + ""
        );
    }
    
    private void updateStartThicknessLabel() {
        startThicknessLabel.setText(
            startThicknessSlider.getValue() + ""
        );
    }
    
    private void updateThicknessChangeLabel() {
        thicknessChangeLabel.setText(
            "-" + thicknessChangeSlider.getValue()
        );
    }
    
    private void updateLinesToBeDrawnLabel() {
        linesToBeDrawnLabel.setText(
            calculateLinesToBeDrawn().toString()
        );
    }
    
    
    private BigInteger calculateLinesToBeDrawn() {
        int levels = levelCountSlider.getValue();
        BigInteger branches = BigInteger.valueOf(
            branchesCountSlider.getValue()
        );
        
        return branches.pow(levels + 1)
                       .subtract(BigInteger.ONE);
    }
    
    public TreeSettings buildTreeSettings() {
        List<BranchSetting> branchSettings =
            settingsPaneManager.getAllBranchSettings();
        
        final int branchLevels = levelCountSlider.getValue();
        final int startLength = startLengthSlider.getValue();
        final int startThickness = startThicknessSlider.getValue();
        final int thicknessChange = -thicknessChangeSlider.getValue();
//        return TreeSettings.getDefaultTreeSettings();
        
        return new TreeSettings(
            branchLevels,
            startLength,
            startThickness,
            thicknessChange,
            TreeSettings.DEFAULT_START_COLOR,
            TreeSettings.DEFAULT_END_COLOR,
            branchSettings
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
