import Shape.JFrameTokenShape;
import Shape.ShapeSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class ColumnObjectHolder {
    public double mostLeftPoint;
    public double mostRightPoint;

    ArrayList<JFrameTokenShape> rowList = new ArrayList<JFrameTokenShape>();

    public JFrameTokenShape addTokenShape(int columnIndex, int rowIndex){
        JFrameTokenShape shape = new JFrameTokenShape(ShapeSettings.DefaultJframeTokenShapeBackColor,50,columnIndex,rowIndex);
        rowList.add(shape);
        return  shape;
    }

    public void setPoint(double mostLeftPoint, double mostRightPoint) {
        this.mostLeftPoint = mostLeftPoint;
        this.mostRightPoint = mostRightPoint;
    }

    public void changeBackGroundColor(){
        for(JFrameTokenShape token:rowList){
            token.changeBackgroundColor(ShapeSettings.MouseMoveJframeTokenShapeBackColor);
        }
    }

    public void setDefaultBackgroundColor(){
        for(JFrameTokenShape token:rowList){
            token.changeBackgroundColor(ShapeSettings.DefaultJframeTokenShapeBackColor);
        }
    }

    public JFrameTokenShape findElement(){
        for(var i = rowList.size()-1; i>=0;i--){
            if(!rowList.get(i).used){
                return  rowList.get(i);
            }
        }
        return  null;
    }
}
