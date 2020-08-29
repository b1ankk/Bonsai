package com.mos.frame.forms;

import javax.swing.*;

public class BranchSetting {
    private JPanel rootPanel;
    private JLabel angleDifferenceLabel;
    private JSlider angleDifferenceSlider;
    private JSpinner lengthChangeSpinner;
    
    public BranchSetting() {
        init();
        addListeners();
    }
    
    private void init() {
        angleDifferenceLabel.setText(
            angleDifferenceSlider.getValue() + ""
        );
        
        
    }
    
    private void addListeners() {
        angleDifferenceSlider.addChangeListener(
            e -> angleDifferenceLabel.setText(
                angleDifferenceSlider.getValue() + ""
            )
        );
    }
    
    
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
