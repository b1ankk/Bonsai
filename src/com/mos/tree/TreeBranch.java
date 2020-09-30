package com.mos.tree;

import com.mos.Drawable;
import com.mos.RecursiveDrawable;
import com.mos.tree.settings.BranchSetting;
import com.mos.tree.settings.TreeSettings;
import com.mos.util.Maths;
import com.mos.util.Point;

import java.awt.*;

import static java.lang.Math.*;

public class TreeBranch implements RecursiveDrawable, Drawable {
    private final TreeNode parentNode;
    private final TreeNode childNode;
    
    private double length;
    private double angle;
    private int thickness;
    private Color color;
    
    public TreeBranch(TreeNode parentNode, double angle, double length,
                      int thickness, Color color)
    {
        this.parentNode = parentNode;
        this.angle = angle;
        this.length = length;
        this.thickness = thickness;
        this.color = color;
        
        this.childNode = new TreeNode(
            calculateEndPoint(),
            parentNode.getLevel() + 1
        );
    }
    
    private Point calculateEndPoint() {
        final int endX = getStartPoint().getX() + (int) round(cos(angle) * length);
        final int endY = getStartPoint().getY() - (int) round(sin(angle) * length);
        return new Point(endX, endY);
    }
    
    public Point getStartPoint() {
        return parentNode.getPosition();
    }
    
    public Point getEndPoint() {
        return childNode.getPosition();
    }
    
    public double getLength() {
        return length;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public TreeNode getParentNode() {
        return parentNode;
    }
    
    public TreeNode getChildNode() {
        return childNode;
    }
    
    public int getLevel() {
        return getParentNode().getLevel();
    }
    
    public void grow(TreeSettings settings) {
        if (parentNode.getLevel() >= settings.getTargetGrowthLevel())
            return;
        
        for (int i = 0; i < settings.getBranchAmount(); ++i) {
            addChildBranch(settings, i);
        }
        
        childNode.growAll(settings);
    }
    
    private void addChildBranch(TreeSettings settings, int branchIndex) {
        BranchSetting setting = settings.getSettingForBranch(branchIndex);
        final int thickness = this.thickness + settings.getThicknessChange();
        final Color color = Maths.lerpColor(
            settings.getStartColor(),
            settings.getEndColor(),
            (float) (parentNode.getLevel() + 1) / settings.getTargetGrowthLevel()
        );
        
        TreeBranch branch = new TreeBranch(
            childNode,
            angle + setting.getAngleDifference(),
            length * setting.getLengthChange(),
            thickness,
            color
        );
        
        childNode.addChildBranch(branch);
    }
    
    private Stroke makeStroke() {
        final int cap = parentNode.getLevel() == 0 ?
                        BasicStroke.CAP_SQUARE :
                        BasicStroke.CAP_ROUND;
        
        Stroke stroke = new BasicStroke(
            Math.max(thickness, 1),
            cap,
            BasicStroke.JOIN_MITER
        );
        
        return stroke;
    }
    
    @Override
    public void drawRecursive(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(makeStroke());
        
        g2d.drawLine(
            getStartPoint().getX(), getStartPoint().getY(),
            getEndPoint().getX(), getEndPoint().getY()
        );
        
        if (childNode != null)
            childNode.drawRecursive(g2d);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(makeStroke());
    
        g2d.drawLine(
            getStartPoint().getX(), getStartPoint().getY(),
            getEndPoint().getX(), getEndPoint().getY()
        );
    }
}
