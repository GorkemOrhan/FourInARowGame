import javax.swing.*;
import java.awt.*;
import Shape.JFrameTokenShape;

public class FrameControl extends JFrame{
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;

    public JPanel panel;

    public FrameControl(){
        panel = new JPanel(new GridLayout(ROWS,COLUMNS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        for (int i = 0;i<COLUMNS;i++){
            Column column = new Column();
            for(int j = 0;j<ROWS;j++){
                JFrameTokenShape shape = column.addTokenShape(i,j);
                panel.add(shape);
            }
        }
        add(panel);
    }
}
