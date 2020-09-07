package com.mos.tree;

import com.mos.Drawable;
import com.mos.tree.settings.BranchSetting;
import com.mos.tree.settings.TreeSettings;
import com.mos.util.Maths;
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
    
    private int thickness;
    private int thicknessChange;
    
    private Color color;
    
    public TreeBranch(TreeNode parentNode, Point startPoint, double angle, double length,
                      int thickness, Color color)
    {
        this.parentNode = parentNode;
        this.startPoint = startPoint;
        this.angle = angle;
        this.length = length;
        this.thickness = thickness;
        this.color = color;
        
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
    
    public void grow(TreeSettings settings) {
        if (parentNode.getLevel() >= settings.getTargetGrowthLevel())
            return;
        
        childNode = new TreeNode(this, parentNode.getLevel() + 1);
        
        for (int i = 0; i < settings.getBranchAmount(); ++i) {
            BranchSetting setting = settings.getSettingForBranch(i);
            final int thickness = this.thickness + settings.getThicknessChange();
            final Color color = Maths.lerpColor(
                settings.getStartColor(),
                settings.getEndColor(),
                (float) (parentNode.getLevel() + 1) / settings.getTargetGrowthLevel()
            );
            
            TreeBranch branch = new TreeBranch(
                childNode,
                endPoint,
                angle + setting.getAngleDifference(),
                length * setting.getLengthChange(),
                thickness,
                color
            );
            
            childNode.addChildBranch(branch);
        }
        
        childNode.growAll(settings);
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
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(makeStroke());
        
        g2d.drawLine(
            startPoint.getX(), startPoint.getY(),
            endPoint.getX(), endPoint.getY()
        );
        
        if (childNode != null)
            childNode.draw(g2d);
    }
}
