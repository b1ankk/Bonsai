package com.mos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::startFrame);
    }
    
    private static void startFrame() {
        Frame frame = new Frame();
    }
}
