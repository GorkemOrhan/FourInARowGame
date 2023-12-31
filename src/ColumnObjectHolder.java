import Shape.JFrameTokenShape;
import Shape.ShapeSettings;

import java.awt.*;
import java.util.ArrayList;


public class ColumnObjectHolder {
    public double mostLeftPoint;
    public double mostRightPoint;
    public int index;

    public ColumnObjectHolder(int index) {
        this.index = index;

    }

    ArrayList<JFrameTokenShape> rowList = new ArrayList<JFrameTokenShape>();

    public JFrameTokenShape addTokenShape(int columnIndex, int rowIndex) {
        JFrameTokenShape shape = new JFrameTokenShape(ShapeSettings.DefaultJframeTokenShapeBackColor, 50, columnIndex, rowIndex);
        rowList.add(shape);
        return shape;
    }

    public void setPoint(double mostLeftPoint, double mostRightPoint) {
        this.mostLeftPoint = mostLeftPoint;
        this.mostRightPoint = mostRightPoint;
    }

    public void changeBackGroundColor() {
        for (JFrameTokenShape token : rowList) {
            token.changeBackgroundColor(ShapeSettings.MouseMoveJframeTokenShapeBackColor);
        }
    }

    public void setDefaultBackgroundColor() {
        for (JFrameTokenShape token : rowList) {
            token.changeBackgroundColor(ShapeSettings.DefaultJframeTokenShapeBackColor);
        }
    }

    public JFrameTokenShape findUnusedLastElement() {
        for (var i = rowList.size() - 1; i >= 0; i--) {
            if (!rowList.get(i).used) {
                return rowList.get(i);
            }
        }
        return null;
    }

    private ArrayList<JFrameTokenShape> getUsedRowList() {
        var usedRowList = new ArrayList<JFrameTokenShape>();
        for (var row : rowList) {
            if (row.used) {
                usedRowList.add(row);
            }
        }
        return usedRowList;
    }

    public boolean hasVerticalMatch(Color tokenColor) {
        var usedRowList = getUsedRowList();
        int counter = 0;
        for (int i = usedRowList.size() - 1; i >= 0; i--) {
            var tokenBackgroundColor = usedRowList.get(i).backgroundColor;
            if (tokenBackgroundColor == tokenColor) {
                counter++;
                if (counter == 4) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;

    }
}