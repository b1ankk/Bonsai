package com.mos.tree.settings;

import java.awt.*;

public class BranchSetting implements Cloneable {
    private double angleDifference = 0;
    private Color color = Color.GREEN;
    private double lengthChange = 0.85;
    
    public double getAngleDifference() {
        return angleDifference;
    }
    
    public Color getColor() {
        return color;
    }
    
    public double getLengthChange() {
        return lengthChange;
    }
    
    @Override
    public BranchSetting clone() {
        try {
            return (BranchSetting) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public CloneManager getCloneManager() {
        return new CloneManager(this);
    }
    
    public static class CloneManager {
        private final BranchSetting newInstance;
        private boolean valid = true;
        
        private CloneManager(BranchSetting thisSetting) {
            newInstance = thisSetting.clone();
        }
        
        public CloneManager withAngleDifference(double angleDifference) {
            newInstance.angleDifference = angleDifference;
            return this;
        }
        
        public CloneManager withColor(Color color) {
            newInstance.color = color;
            return this;
        }
        
        public CloneManager withLengthChange(double lengthChange) {
            newInstance.lengthChange = lengthChange;
            return this;
        }
        
        public BranchSetting retrieve() {
            if (!valid)
                throw new IllegalStateException("The clone has been already retrieved");
            
            valid = false;
            return newInstance;
        }
    }
    
    
    
    
    
}
