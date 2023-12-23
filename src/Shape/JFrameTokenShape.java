package Shape;

import java.awt.*;

public class JFrameTokenShape extends TokenShape {

    public int columnIndex;
    public int rowIndex;

    public boolean used = false;

    protected boolean isUsed() {

        return used;
    }

    public void markAsUsed(Color backgroundColor) {
        changeBackgroundColor(backgroundColor);
        used = true;
    }

    public JFrameTokenShape(Color backgroundColor,int radius, int columnIndex, int rowIndex) {
        super(radius,backgroundColor);
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }

    public void changeBackgroundColor(Color backgroundColor){
        if(!used){
            this.backgroundColor = backgroundColor;
            repaint();
        }
    }
}
