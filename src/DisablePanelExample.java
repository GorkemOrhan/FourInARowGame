import javax.swing.*;
import java.awt.*;

    public class DisablePanelExample {
        public static void main(String[] args) {
            JFrame frame = new JFrame("Disable Panel Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            JPanel panel = new JPanel();
            panel.add(new JButton("Button"));
            panel.add(new JTextField("TextField", 10));

            frame.add(panel);
            frame.setVisible(true);

            // Paneli ve içindeki bileşenleri devre dışı bırak
            setPanelEnabled(panel, false);
        }

        public static void setPanelEnabled(JPanel panel, boolean enabled) {
            panel.setEnabled(enabled);
            Component[] components = panel.getComponents();
            for (Component component : components) {
                component.setEnabled(enabled);
                if (component instanceof Container) {
                    setPanelEnabled((JPanel) component, enabled); // Eğer bileşen bir konteyner ise (örneğin başka bir JPanel), onu da devre dışı bırak
                }
            }
        }
    }

