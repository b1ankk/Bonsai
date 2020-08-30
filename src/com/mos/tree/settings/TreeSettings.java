package com.mos.tree.settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TreeSettings implements Cloneable {
    
    private final int targetGrowthLevel;
    private final List<BranchSetting> branchSettings;
    
    public TreeSettings(int targetGrowthLevel, List<BranchSetting> branchSettings) {
        this.targetGrowthLevel = targetGrowthLevel;
        this.branchSettings = branchSettings;
    }
    
    public static TreeSettings getDefaultTreeSettings() {
        List<BranchSetting> branchSettings = new ArrayList<>(2);
        
        BranchSetting setting1 = new BranchSetting()
            .getCloneManager()
            .withAngleDifference(-Math.PI / 8)
            .withColor(Color.GREEN)
            .withLengthChange(0.85)
            .withThickness(3)
            .retrieve();
        
        BranchSetting setting2 = setting1
            .getCloneManager()
            .withAngleDifference(Math.PI / 6)
            .withLengthChange(0.8)
            .retrieve();
        
        branchSettings.add(setting1);
        branchSettings.add(setting2);
        
        return new TreeSettings(12, branchSettings);
    }
    
    public int getTargetGrowthLevel() {
        return targetGrowthLevel;
    }
    
    public int getBranchAmount() {
        return branchSettings.size();
    }
    
    public BranchSetting getSettingForBranch(int index) {
        return branchSettings.get(index);
    }
    
    @Override
    public TreeSettings clone() {
        try {
            return (TreeSettings) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
