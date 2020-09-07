package com.mos.tree.settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TreeSettings implements Cloneable {
    public final static Color DEFAULT_START_COLOR = new Color(0x68493B);
    public final static Color DEFAULT_END_COLOR = new Color(0x0ED900);
    
    
    private final int targetGrowthLevel;
    private final int startLength;
    
    private final int startThickness;
    private final int thicknessChange;
    
    private final Color startColor;
    private final Color endColor;
    
    private final List<BranchSetting> branchSettings;
    
    public TreeSettings(int targetGrowthLevel,
                        int startLength,
                        int startThickness,
                        int thicknessChange,
                        Color startColor,
                        Color endColor,
                        List<BranchSetting> branchSettings) {
        this.targetGrowthLevel = targetGrowthLevel;
        this.startLength = startLength;
        this.startThickness = startThickness;
        this.thicknessChange = thicknessChange;
        this.startColor = startColor;
        this.endColor = endColor;
        this.branchSettings = branchSettings;
    }
    
    public static TreeSettings getDefaultTreeSettings() {
        List<BranchSetting> branchSettings = new ArrayList<>(2);
        
        BranchSetting setting1 = new BranchSetting()
            .getCloneManager()
            .withAngleDifference(-Math.PI / 8)
            .withColor(Color.GREEN)
            .withLengthChange(0.85)
            .retrieve();
        
        BranchSetting setting2 = setting1
            .getCloneManager()
            .withAngleDifference(Math.PI / 6)
            .withLengthChange(0.8)
            .retrieve();
        
        branchSettings.add(setting1);
        branchSettings.add(setting2);
        
        final int defaultGrowthLevel = 12;
        final int defaultStartLength = 80;
        final int defaultStartThickness = 14;
        final int defaultThicknessChange = -1;
        
        return new TreeSettings(
            defaultGrowthLevel,
            defaultStartLength,
            defaultStartThickness,
            defaultThicknessChange,
            DEFAULT_START_COLOR,
            DEFAULT_END_COLOR,
            branchSettings
        );
    }
    
    public int getTargetGrowthLevel() {
        return targetGrowthLevel;
    }
    
    public int getBranchAmount() {
        return branchSettings.size();
    }
    
    public int getStartLength() {
        return startLength;
    }
    
    public int getStartThickness() {
        return startThickness;
    }
    
    public int getThicknessChange() {
        return thicknessChange;
    }
    
    public Color getStartColor() {
        return startColor;
    }
    
    public Color getEndColor() {
        return endColor;
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
