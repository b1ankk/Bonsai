package com.mos.frame.components;

import com.mos.frame.forms.BranchSettingPane;
import com.mos.tree.settings.BranchSetting;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BranchSettingsTabbedPaneManager {
    private final BranchSettingsTabbedPane parentSettingsPane;
    
    private final ArrayList<BranchSettingPane> branchSettingsBuffer = new ArrayList<>();
    private int nextPanelIndex = 0;
    
    public BranchSettingsTabbedPaneManager() {
        parentSettingsPane = new BranchSettingsTabbedPane();
    }
    
    public void updateTabsCount(int targetCount) {
        if (targetCount < 0)
            throw new IllegalArgumentException("Tabs count can't be less than zero");
        
        if (targetCount > parentSettingsPane.getTabCount())
            addTabsUntilCount(targetCount);
        else if (targetCount < parentSettingsPane.getTabCount())
            removeTabsUntilCount(targetCount);
    }
    
    private void removeTabsUntilCount(int targetCount) {
        if (targetCount == 0) {
            parentSettingsPane.removeAll();
            nextPanelIndex = 0;
        }
        else while (targetCount < parentSettingsPane.getTabCount()) {
            final int tabToRemoveIndex = parentSettingsPane.getTabCount() - 1;
            parentSettingsPane.removeTabAt(tabToRemoveIndex);
            --nextPanelIndex;
        }
        
    }
    
    private void addTabsUntilCount(int targetCount) {
        while (targetCount > parentSettingsPane.getTabCount()) {
            final boolean availableInBuffer =
                nextPanelIndex < branchSettingsBuffer.size();
            
            if (availableInBuffer)
                addRetrievedSettingsTab();
            else addNewSettingTab();
        }
    }
    
    private void addNewSettingTab() {
        final String nextTabName = getNextTabName();
        final BranchSettingPane newSettingsPane =
            new BranchSettingPane(new BranchSetting());
        
        parentSettingsPane.addTab(
            nextTabName,
            newSettingsPane.getRootPanel()
        );
        
        branchSettingsBuffer.add(newSettingsPane);
        ++nextPanelIndex;
    }
    
    private void addRetrievedSettingsTab() {
        final String nextTabName = getNextTabName();
        final BranchSettingPane retrievedSettingsPanel =
            branchSettingsBuffer.get(nextPanelIndex);
    
        parentSettingsPane.addTab(
            nextTabName,
            retrievedSettingsPanel.getRootPanel()
        );
        
        ++nextPanelIndex;
    }
    
    private String getNextTabName() {
        return parentSettingsPane.getTabCount() + 1 + "";
    }
    
    public void addConfigPaneTo(JComponent component) {
        component.add(parentSettingsPane);
    }
    
    public List<BranchSetting> getAllBranchSettings() {
        final List<BranchSetting> settings = new ArrayList<>(nextPanelIndex);
        
        for (int i = 0; i < nextPanelIndex; ++i) {
            settings.add(
                branchSettingsBuffer.get(i).getModelSetting()
            );
        }
        return Collections.unmodifiableList(settings);
    }
    
}
