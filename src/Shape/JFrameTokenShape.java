package Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.MouseListener;

public class JFrameTokenShape extends TokenShape {

    public int columnIndex;
    public int rowIndex;

    boolean used = false;

    protected boolean isUsed() {

        return used;
    }

    protected void markAsUsed(Color backgroundColor) {
        used = true;
        setBackground(backgroundColor);
    }

    public JFrameTokenShape(int radius, int columnIndex, int rowIndex) {
        super(radius);
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }
}
