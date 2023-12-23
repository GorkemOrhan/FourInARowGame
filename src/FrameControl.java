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
        mainLayoutPanel.setBackground(Color.DARK_GRAY);
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

        add(mainLayoutPanel);
        MouseMotionHandler mouseMotionHandler =new MouseMotionHandler();
        MouseHandler mouseHandler =new MouseHandler();
        mainLayoutPanel.addMouseMotionListener(mouseMotionHandler);
        mainLayoutPanel.addMouseListener(mouseHandler);
    }
    public class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

            Thread thread = new Thread(){
                @Override
                public void run() {
                    int mouseX = e.getX();
                    for(ColumnObjectHolder columnObjectHolder:columnObjectHolderList){
                        if(mouseX>=columnObjectHolder.mostLeftPoint && mouseX<=columnObjectHolder.mostRightPoint){
                            columnObjectHolder.changeBackGroundColor();
                            continue;
                        }
                        columnObjectHolder.setDefaultBackgroundColor();
                    }
                }
            };
            thread.start();
        }
    }

    public class MouseHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    int mouseX = e.getX();
                    ColumnObjectHolder selectedColumnObjectHolder = null;
                    for(ColumnObjectHolder columnObjectHolder:columnObjectHolderList){
                        if(mouseX>=columnObjectHolder.mostLeftPoint && mouseX<=columnObjectHolder.mostRightPoint){
                            selectedColumnObjectHolder = columnObjectHolder;
                            break;
                        }
                    }

                    if(selectedColumnObjectHolder!=null){
                        var tokenElement = selectedColumnObjectHolder.findElement();
                        if(tokenElement!=null){
                            tokenElement.markAsUsed(Color.blue);
                        }
                    }
                }
            };
            thread.start();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
