package view;
import java.awt.*;
import javax.swing.border.Border;

public class RoundedBorder implements Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        int padding = 10; // Adjust padding as needed
        return new Insets(this.radius + padding, this.radius + padding, this.radius + padding, this.radius + padding);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}