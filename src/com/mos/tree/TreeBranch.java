package com.mos.tree;

import com.mos.Drawable;
import com.mos.util.Point;

import java.awt.*;

import static java.lang.Math.*;

public class TreeBranch implements Drawable {
    private final TreeNode parentNode;
    private TreeNode childNode;
    
    private final Point startPoint;
    private final Point endPoint;
    
    private double length;
    private double angle;
    
    public TreeBranch(TreeNode parentNode, Point startPoint, double angle, double length) {
        this.parentNode = parentNode;
        this.startPoint = startPoint;
        this.angle = angle;
        this.length = length;
        
        this.endPoint = calculateEndPoint();
    }
    
    private Point calculateEndPoint() {
        final int endX = startPoint.getX() + (int) round(cos(angle) * length);
        final int endY = startPoint.getY() - (int) round(sin(angle) * length);
        return new Point(endX, endY);
    }
    
    public Point getStartPoint() {
        return startPoint;
    }
    
    public Point getEndPoint() {
        return endPoint;
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
    
    public void grow(BranchGrowthConfig config) {
        if (parentNode.getLevel() >= config.getTargetGrowthLevel())
            return;
        
        childNode = new TreeNode(this, parentNode.getLevel() + 1);
        
        for (int i = 0; i < config.getBranchAmount(); ++i) {
            BranchGrowthConfig.BranchSetting setting = config.getSettingForBranch(i);
            
            TreeBranch branch = new TreeBranch(
                childNode,
                endPoint,
                angle + setting.getAngleDifference(),
                length * setting.getLengthChange()
            );
            
            childNode.addChildBranch(branch);
        }
        
        childNode.growAll(config);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawLine(
            startPoint.getX(), startPoint.getY(),
            endPoint.getX(), endPoint.getY()
        );
        
        if (childNode != null)
            childNode.draw(g2d);
    }
}
