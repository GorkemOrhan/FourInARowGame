import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import Shape.JFrameTokenShape;

public class FrameControl extends JFrame {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public JPanel panel;
    public ArrayList<Column> columns;

    public FrameControl(){
        panel = new JPanel(new GridLayout(ROWS,COLUMNS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        columns = new ArrayList<Column>();
        for (int i = 0;i<COLUMNS;i++){
            Column column = new Column();
            for(int j = 0;j<ROWS;j++){
                JFrameTokenShape shape = column.addTokenShape(i,j);
                panel.add(shape);
            }
            columns.add(column);
        }
        add(panel);
        MouseHandler handler =new MouseHandler();
        panel.addMouseMotionListener(handler);
    }
}
