import Shape.JFrameTokenShape;
import Shape.ShapeSettings;

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

    public boolean hasDiagonalSameElements(ColumnObjectHolder columnObjectHolder,JFrameTokenShape jFrameTokenShape) {
        var rowIndex = jFrameTokenShape.rowIndex;

        var tempNextColumnholder = columnObjectHolder;
        ArrayList<JFrameTokenShape> elementList = new ArrayList<JFrameTokenShape>();
        if(this.size()-1 != columnObjectHolder.index){
            for (var i = 0; i<=10;i++){
                var nextColumnholder = findNext(tempNextColumnholder);
                if(nextColumnholder!=null){
                    tempNextColumnholder =nextColumnholder;
                    var nextElement = tempNextColumnholder.rowList.get(rowIndex-i);
                    elementList.add(nextElement);
                }
            }
        }


        var tempPreviousColumnholder = columnObjectHolder;
        if(columnObjectHolder.index >0){
            for (var i = 0; i<=10;i++){
                var previousColumnholder = findPrevious(tempPreviousColumnholder);
                if(previousColumnholder!=null){
                    tempPreviousColumnholder = previousColumnholder;
                    if(tempPreviousColumnholder.rowList.size()-1 > rowIndex+i){
                        var nextElement = tempPreviousColumnholder.rowList.get(rowIndex+i);
                        elementList.add(nextElement);
                    }
                }
            }
        }

        if(elementList.size()<4){
            return false;
        }

        var currentElementBgc = jFrameTokenShape.backgroundColor;
        for (int i = 0; i <= elementList.size() - 4; i++) {
            var firstNextBgc =  this.get(i+1).rowList.get(rowIndex).backgroundColor;
            var secondNextBgc =  this.get(i+2).rowList.get(rowIndex).backgroundColor;
            var thirdNextBgc =  this.get(i+3).rowList.get(rowIndex).backgroundColor;

            if (currentElementBgc!= ShapeSettings.DefaultJframeTokenShapeBackColor && currentElementBgc == firstNextBgc && currentElementBgc ==secondNextBgc && currentElementBgc== thirdNextBgc) {
                return true;
            }
        }

        return  false;
    }

     public boolean hasConsecutiveSameElements(JFrameTokenShape jFrameTokenShape) {
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
