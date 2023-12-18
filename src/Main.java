import java.awt.*;
import Shape.JFrameTokenShape;

public class Main {
    public static void main(String[] args) {
        FrameControl frameControl = new FrameControl();
        frameControl.setVisible(true);

        for (Column columnObject:frameControl.columns){
            JFrameTokenShape firstTokenByColumn = columnObject.columnList.get(0);
            columnObject.setLocation(firstTokenByColumn.getCircleScreenLocation());

            System.out.println("columnIndex:"+firstTokenByColumn.columnIndex+"---------"+"mostLeftPoint:"+columnObject.mostLeftPoint+", mostRightPoint:"+columnObject.mostRightPoint);
        }

        Component[] components = frameControl.panel.getComponents();

     /*   for (Component comp:components){
            JFrameTokenShape shape =  (JFrameTokenShape)comp;
            System.out.println(shape.getCircleScreenLocation());
        } */
    }
}