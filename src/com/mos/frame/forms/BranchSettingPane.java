package com.mos.frame.forms;

import com.mos.tree.settings.BranchSetting;

import javax.swing.*;

public class BranchSettingPane {
    private JPanel rootPanel;
    private JLabel angleDifferenceLabel;
    private JSlider angleDifferenceSlider;
    private JLabel lengthChangeLabel;
    private JSlider lengthChangeSlider;
    private JButton resetToDefaultsButton;
    private JButton copyToAllButton;
    
    private BranchSetting modelSetting;
    
    public BranchSettingPane(BranchSetting modelSetting) {
        this.modelSetting = modelSetting;
        
        init();
        resetToSetting(this.modelSetting);
    }
    
    private void init() {
        updateUI();
        addListeners();
    }
    
    private void addListeners() {
        angleDifferenceSlider.addChangeListener(
            e -> updateAngleDifferenceLabel()
        );
        
        lengthChangeSlider.addChangeListener(
            e -> updateLengthChangeLabel()
        );
        
        resetToDefaultsButton.addActionListener(
            e -> resetToDefaults()
        );
    }
    
    private void updateUI() {
        updateAngleDifferenceLabel();
        updateLengthChangeLabel();
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
    
    private void resetToDefaults() {
        resetToSetting(new BranchSetting());
    }
    
    private void resetToSetting(BranchSetting setting) {
        modelSetting = setting;
        
        angleDifferenceSlider.setValue(
            ((int) Math.round(
                Math.toDegrees(modelSetting.getAngleDifference())
            ))
        );
        lengthChangeSlider.setValue(
            ((int) (modelSetting.getLengthChange() * 100))
        );
    }
    
    private double getAngleDifference() {
        return Math.toRadians(
            angleDifferenceSlider.getValue()
        );
    }
    
    private double getLengthChange() {
        return lengthChangeSlider.getValue() / 100.;
    }
    
    private void updateModelSetting() {
        modelSetting = modelSetting.getCloneManager()
            .withAngleDifference(getAngleDifference())
            .withLengthChange(getLengthChange())
            .retrieve();
    }
    
    public BranchSetting getModelSetting() {
        updateModelSetting();
        return modelSetting;
    }
    
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
