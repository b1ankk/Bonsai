package com.mos.tree;

import java.awt.*;
import java.util.ArrayList;

public class BranchGrowthConfig implements Cloneable {
    
    private final int targetGrowthLevel;
    private final ArrayList<BranchSetting> particularBranchSettings = new ArrayList<>();
    
    public BranchGrowthConfig(int targetGrowthLevel) {
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
    public BranchGrowthConfig clone() {
        try {
            return (BranchGrowthConfig) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static class BranchSetting implements Cloneable {
        private double angleDifference = 0;
        private Color color = Color.GREEN;
        private double lengthChange = 0.85;
        private int thickness = 3;
    
        
        public BranchSetting withAngleDifference(double angleDifference) {
            BranchSetting setting = this.clone();
            setting.angleDifference = angleDifference;
            return setting;
        }
        
        public BranchSetting withColor(Color color) {
            BranchSetting setting = this.clone();
            setting.color = color;
            return setting;
        }
        
        public BranchSetting withLengthChange(double lengthChange) {
            BranchSetting setting = this.clone();
            setting.lengthChange = lengthChange;
            return setting;
        }
        
        public BranchSetting withThickness(int thickness) {
            BranchSetting setting = this.clone();
            setting.thickness = thickness;
            return setting;
        }
        
        public double getLengthChange() {
            return lengthChange;
        }
    
        public int getThickness() {
            return thickness;
        }
    
        public Color getColor() {
            return color;
        }
    
        public double getAngleDifference() {
            return angleDifference;
        }
    
        @Override
        public BranchSetting clone(){
            try {
                return (BranchSetting) super.clone();
            }
            catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
}
