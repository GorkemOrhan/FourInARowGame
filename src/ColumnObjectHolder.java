import Shape.JFrameTokenShape;

import java.awt.*;
import java.util.ArrayList;

public class ColumnObjectHolder {
    public double mostLeftPoint;
    public double mostRightPoint;
    ArrayList<JFrameTokenShape> rowList = new ArrayList<JFrameTokenShape>();


    public JFrameTokenShape addTokenShape(int columnIndex, int rowIndex){
        JFrameTokenShape shape = new JFrameTokenShape(50,columnIndex,rowIndex);
        rowList.add(shape);
        return  shape;
    }

    public void setPoint(double mostLeftPoint, double mostRightPoint) {
        this.mostLeftPoint = mostLeftPoint;
        this.mostRightPoint = mostRightPoint;
    }

    public void changeBackGroundColor(){
        for(JFrameTokenShape token:rowList){
            token.setBackground(Color.red);
        }
    }

    public void setDefaultBackgroundColor(){
        for(JFrameTokenShape token:rowList){
            token.setBackground(Color.black);
        }
    }

}
