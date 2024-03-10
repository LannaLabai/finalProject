package view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import control.SQLQueries;
import model.Donation;
import model.DonationProject;
import model.Hotel;

class BlurContentPanel extends JPanel {
    public BlurContentPanel() {
        setOpaque(false); // Make the panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set the background color with transparency
        g.setColor(new Color(255, 255, 255, 100)); // Adjust the alpha value (100) to control transparency
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
}