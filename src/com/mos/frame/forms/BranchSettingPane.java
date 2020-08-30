package com.mos.frame.forms;

import javax.swing.*;

public class BranchSettingPane {
    private JPanel rootPanel;
    private JLabel angleDifferenceLabel;
    private JSlider angleDifferenceSlider;
    private JLabel lengthChangeLabel;
    private JSlider lengthChangeSlider;
    private JButton resetToDefaultsButton;
    private JButton copyToAllButton;
    
    public BranchSettingPane() {
        init();
        addListeners();
    }
    
    private void init() {
        updateAngleDifferenceLabel();
        updateLengthChangeLabel();
    }
    
    private void addListeners() {
        angleDifferenceSlider.addChangeListener(
            e -> updateAngleDifferenceLabel()
        );
        
        lengthChangeSlider.addChangeListener(
            e -> updateLengthChangeLabel()
        );
    }
    
    private void updateAngleDifferenceLabel() {
        angleDifferenceLabel.setText(
            angleDifferenceSlider.getValue() + "°"
        );
    }
    
    private void updateLengthChangeLabel() {
        lengthChangeLabel.setText(
            lengthChangeSlider.getValue() + "%"
        );
    }
    
    
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
