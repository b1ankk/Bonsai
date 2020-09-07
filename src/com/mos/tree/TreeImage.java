package com.mos.tree;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class TreeImage extends BufferedImage {
    private final static int DEFAULT_IMAGE_TYPE = BufferedImage.TYPE_4BYTE_ABGR;
    private final static Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    
    private final Graphics2D bufferedGraphics2D;
    
    public TreeImage(int width, int height, int imageType) {
        super(width, height, imageType);
        this.bufferedGraphics2D = createGraphics();
    }
    
    public TreeImage(int width, int height, int imageType, IndexColorModel cm) {
        super(width, height, imageType, cm);
        this.bufferedGraphics2D = createGraphics();
    }
    
    public TreeImage(ColorModel cm, WritableRaster raster,
                     boolean isRasterPremultiplied, Hashtable<?, ?> properties)
    {
        super(cm, raster, isRasterPremultiplied, properties);
        this.bufferedGraphics2D = createGraphics();
    }
    
    
    public static TreeImage getDefaultImage(final int width, final int height) {
        TreeImage treeImage = new TreeImage(
            width, height, DEFAULT_IMAGE_TYPE
        );
        
        treeImage.bufferedGraphics2D.setBackground(DEFAULT_BACKGROUND_COLOR);
        treeImage.bufferedGraphics2D.clearRect(0, 0, width, height);
        
        return treeImage;
    }
    
    
    public void clear() {
        bufferedGraphics2D.clearRect(
            0, 0, getWidth(), getHeight()
        );
    }
    
    public Graphics2D getBufferedGraphics2D() {
        return bufferedGraphics2D;
    }
    
    @Override
    public Graphics2D createGraphics() {
        Graphics2D g2d = super.createGraphics();
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        return g2d;
    }
}
