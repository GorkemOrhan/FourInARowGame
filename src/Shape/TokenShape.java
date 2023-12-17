package Shape;

import javax.swing.*;
import java.awt.*;

public abstract class TokenShape extends JPanel {

    protected int radius;
    private Graphics2D g2d;


    protected TokenShape(int radius) {
        this.radius = radius;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g.create();
        g2d.fillOval(0, 0, 100, 100);
        g2d.dispose();
    }

    public void setColor(Color color) {
        g2d.setColor(color);
    }
}
