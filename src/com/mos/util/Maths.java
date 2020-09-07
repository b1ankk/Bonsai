package com.mos.util;

import java.awt.*;

public class Maths {
    public static Color lerpColor(Color start, Color end, float fraction) {
        int sr = start.getRed();
        int sg = start.getGreen();
        int sb = start.getBlue();
        
        int er = end.getRed();
        int eg = end.getGreen();
        int eb = end.getBlue();
        
        int r = sr + Math.round((er - sr) * fraction);
        int g = sg + Math.round((eg - sg) * fraction);
        int b = sb + Math.round((eb - sb) * fraction);
        
        return new Color(r, g, b);
    }
}
