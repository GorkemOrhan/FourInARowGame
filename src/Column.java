import Shape.JFrameTokenShape;

import java.util.ArrayList;

public class Column {
    private double mostLeftPoint;
    private double mostRightPoint;
    ArrayList<JFrameTokenShape> columnList = new ArrayList<JFrameTokenShape>();

    public JFrameTokenShape addTokenShape(int columnIndex, int rowIndex){
        JFrameTokenShape shape = new JFrameTokenShape(10,columnIndex,rowIndex);
        columnList.add(shape);
        return  shape;
    }
}
