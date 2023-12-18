import Shape.JFrameTokenShape;
import Shape.TokenShape;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Column {
    public double mostLeftPoint;
    public double mostRightPoint;
    ArrayList<JFrameTokenShape> columnList = new ArrayList<JFrameTokenShape>();


    public JFrameTokenShape addTokenShape(int columnIndex, int rowIndex){
        JFrameTokenShape shape = new JFrameTokenShape(50,columnIndex,rowIndex);
        columnList.add(shape);
        return  shape;
    }

    public void setLocation(Point location) {
        mostLeftPoint = location.getX()-50;
        mostRightPoint = location.getX()+50;
    }
}
