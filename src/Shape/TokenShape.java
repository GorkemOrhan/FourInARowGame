package Shape;

import javax.swing.*;
import java.awt.*;

public abstract class TokenShape extends JPanel {

    protected int radius;
    protected  Color backgroundColor;

    protected TokenShape(int radius, Color backgroundColor) {
        this.radius = radius;
        this.backgroundColor = backgroundColor;
        setBackground(Color.DARK_GRAY);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - radius) / 2;
        int y = (getHeight() - radius) / 2;
        g.setColor(backgroundColor);
        g.fillOval(x, y, radius, radius);
    }
}
