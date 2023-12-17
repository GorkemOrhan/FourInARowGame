package Shape;

import java.awt.*;

public class JFrameTokenShape extends TokenShape{

    int columnIndex;
    int rowIndex;

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

    public double mostXLeft = getLocation().x;
    public double mostXRight = getLocation().x+100;

}
