import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import Shape.JFrameTokenShape;

public class FrameControl extends JFrame  {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public JPanel mainLayoutPanel;
    public ArrayList<ColumnObjectHolder> columnObjectHolderList;

    public FrameControl(){
        mainLayoutPanel = new JPanel(new GridLayout(ROWS,COLUMNS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        columnObjectHolderList = new ArrayList<ColumnObjectHolder>();

        for (var i=0; i<COLUMNS;i++){
            ColumnObjectHolder columnObjectHolder = new ColumnObjectHolder();
            columnObjectHolderList.add(columnObjectHolder);
        }

        for (int i = 0;i<ROWS;i++){
            for(int j = 0;j<COLUMNS;j++){
                var columnObjectHolder = columnObjectHolderList.get(j);
                JFrameTokenShape shape = columnObjectHolder.addTokenShape(j,i);
                mainLayoutPanel.add(shape);
            }
        }
      /*  for(ColumnObjectHolder columnObject:columnObjectHolderList){
            var firstShapeByColumn = columnObject.rowList.get(0);
            firstShapeByColumn.setBackground(Color.red);
        }*/
        add(mainLayoutPanel);
        MouseHandler handler =new MouseHandler();
        mainLayoutPanel.addMouseMotionListener(handler);
    }
    public class MouseHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

            Thread thread = new Thread(){
                @Override
                public void run() {
                    int mouseX = e.getX();
                    System.out.println(mouseX);
                    for(ColumnObjectHolder columnObjectHolder:columnObjectHolderList){
                        if(mouseX>=columnObjectHolder.mostLeftPoint && mouseX<=columnObjectHolder.mostRightPoint){
                            columnObjectHolder.changeBackGroundColor();
                        }

                        columnObjectHolder.setDefaultBackgroundColor();
                    }
                }
            };
            thread.start();
        }
    }
}
