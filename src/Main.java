import java.awt.*;
import Shape.JFrameTokenShape;

public class Main {
    public static void main(String[] args) {
        FrameControl frameControl = new FrameControl();
        frameControl.setVisible(true);

       var mainLayoutPanelWidth = frameControl.mainLayoutPanel.getSize().width;

       var tokenShapeWidth = mainLayoutPanelWidth/FrameControl.COLUMNS;

        for (var i =0; i<frameControl.columnObjectHolderList.size();i++){
            var mostLeftX = tokenShapeWidth * i;
            var mostRightX = mostLeftX+tokenShapeWidth;
            frameControl.columnObjectHolderList.get(i).setPoint(mostLeftX,mostRightX);
        }
    }
}