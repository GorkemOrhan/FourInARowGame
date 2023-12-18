package Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;

public abstract class TokenShape extends JPanel {

    protected int radius;
    private Graphics2D g2d;

    public TokenShape(int radius) {
        this.radius = radius;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (getWidth() - radius) / 2; // X-coordinate for the circle
        int y = (getHeight() - radius) / 2; // Y-coordinate for the circle
        g.fillOval(x, y, radius, radius); // Draw the circle
    }

    public Point getCircleScreenLocation() {
        //int x = (getWidth() - radius) / 2; // Dairenin x koordinatı (CircleComponent içinde)
        //int y = (getHeight() - radius) / 2; // Dairenin y koordinatı (CircleComponent içinde)

        // CircleComponent'in ekran üzerindeki konumunu al
        //Point locationOnScreen = getLocationOnScreen();
        Point location = getLocation();
        // Dairenin ekran üzerindeki x ve y konumlarını hesapla ve döndür
        return new Point(location.x , location.y);
    }

    public void setColor(Color color) {
        g2d.setColor(color);
    }
}
