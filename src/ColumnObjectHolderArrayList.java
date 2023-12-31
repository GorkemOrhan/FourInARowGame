import Shape.JFrameTokenShape;
import Shape.ShapeSettings;

import java.awt.*;
import java.util.ArrayList;

public class ColumnObjectHolderArrayList extends ArrayList<ColumnObjectHolder> {


    public ColumnObjectHolder findNext(ColumnObjectHolder columnObjectHolder){

        if(this.size()-1 == columnObjectHolder.index){
            return null;
        }

        return this.get(columnObjectHolder.index+1);
     }

    public ColumnObjectHolder findPrevious(ColumnObjectHolder columnObjectHolder){

        if(columnObjectHolder.index == 0){
            return null;
        }

        return this.get(columnObjectHolder.index-1);
    }

    public boolean hasDiagonalMatch(JFrameTokenShape jFrameTokenShape) {
        int rowIndex = jFrameTokenShape.rowIndex;
        int columnIndex = jFrameTokenShape.columnIndex;
        Color currentColor = jFrameTokenShape.backgroundColor;
        int maxRow = 6; // Satır sayısı
        int maxCol = 7; // Sütun sayısı

        // Her token için diyagonal kontrolü
        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {
                // Sağ üstten sol alta doğru kontrol
                if (col <= maxCol - 4 && row >= 3) {
                    if (checkDiagonal(col, row, -1, currentColor)) {
                        return true;
                    }
                }
                // Sol üstten sağ alta doğru kontrol
                if (col <= maxCol - 4 && row <= maxRow - 4) {
                    if (checkDiagonal(col, row, 1, currentColor)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkDiagonal(int col, int row, int direction, Color color) {
        for (int k = 0; k < 4; k++) {
            JFrameTokenShape shape = this.get(col + k).rowList.get(row + k * direction);
            if (shape.backgroundColor != color) {
                return false;
            }
        }
        return true;
    }

     public boolean hasConsecutiveMatch(JFrameTokenShape jFrameTokenShape) {
         var rowIndex = jFrameTokenShape.rowIndex;

         for (int i = 0; i <= this.size() - 4; i++) {
             var columnObjectHolder = this.get(i);
             var currentElementBgc = columnObjectHolder.rowList.get(rowIndex).backgroundColor;
             var firstNextBgc =  this.get(i+1).rowList.get(rowIndex).backgroundColor;
             var secondNextBgc =  this.get(i+2).rowList.get(rowIndex).backgroundColor;
             var thirdNextBgc =  this.get(i+3).rowList.get(rowIndex).backgroundColor;

             if (currentElementBgc!= ShapeSettings.DefaultJframeTokenShapeBackColor && currentElementBgc == firstNextBgc && currentElementBgc ==secondNextBgc && currentElementBgc== thirdNextBgc) {
                 return true;
             }
         }
         return false;
     }
}
