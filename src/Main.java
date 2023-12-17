import java.awt.*;
import Shape.JFrameTokenShape;

public class Main {
    public static void main(String[] args) {
        FrameControl t = new FrameControl();
        t.setVisible(true);

        Component[] components = t.panel.getComponents();

        for (Component comp:components){
            JFrameTokenShape shape =  (JFrameTokenShape)comp;

            System.out.println("xMin:"+shape.mostXLeft+" - xMax:"+shape.mostXRight);
        }
    }
}