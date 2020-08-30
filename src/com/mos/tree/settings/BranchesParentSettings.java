package com.mos.tree.settings;

import java.util.ArrayList;

public class BranchesParentSettings implements Cloneable {
    
    private final int targetGrowthLevel;
    private final ArrayList<BranchSetting> particularBranchSettings = new ArrayList<>();
    
    public BranchesParentSettings(int targetGrowthLevel) {
        this.targetGrowthLevel = targetGrowthLevel;
    }
    
    public void addBranchSetting(BranchSetting setting) {
        particularBranchSettings.add(setting);
    }
    
    public int getTargetGrowthLevel() {
        return targetGrowthLevel;
    }
    
    public int getBranchAmount() {
        return particularBranchSettings.size();
    }
    
    public BranchSetting getSettingForBranch(int index) {
        return particularBranchSettings.get(index);
    }
    
    @Override
    public BranchesParentSettings clone() {
        try {
            return (BranchesParentSettings) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
}
