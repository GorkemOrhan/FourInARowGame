import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Shape.JFrameTokenShape;

public class FrameControl extends JFrame  {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public JPanel mainLayoutPanel;
    public ColumnObjectHolderArrayList columnObjectHolderList;
    private JLabel playerTurn;
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public JButton startBtn = new JButton("Start");
    public JButton restartBtn = new JButton("Restart");
    public JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    public FrameControl(){
        playerTurn = new JLabel();
        mainLayoutPanel = new JPanel(new GridLayout(ROWS,COLUMNS));
        mainLayoutPanel.setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        columnObjectHolderList = new ColumnObjectHolderArrayList();
        player1 = new Player(Color.BLUE,"First");
        player2 = new Player(Color.ORANGE,"Second");
        currentPlayer = player1;
        turnPlayer();

        for (var i=0; i<COLUMNS;i++){
            ColumnObjectHolder columnObjectHolder = new ColumnObjectHolder(i);
            columnObjectHolderList.add(columnObjectHolder);
        }

        for (int i = 0;i<ROWS;i++){
            for(int j = 0;j<COLUMNS;j++){
                var columnObjectHolder = columnObjectHolderList.get(j);
                JFrameTokenShape shape = columnObjectHolder.addTokenShape(j,i);
                mainLayoutPanel.add(shape);
            }
        }

        add(mainLayoutPanel,BorderLayout.SOUTH);
       // playerTurn.setBounds(200,30,250,250);
        playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
        playerTurn.setFont(new Font("Verdana", Font.PLAIN, 15));
       // playerTurn.setPreferredSize(new Dimension(250, 100));
        buttonPanel.add(startBtn);
        buttonPanel.add(playerTurn);
        buttonPanel.add(restartBtn);
        MouseMotionHandler mouseMotionHandler =new MouseMotionHandler();
        MouseHandler mouseHandler =new MouseHandler();
        ButtonHandler buttonHandler = new ButtonHandler();
        startBtn.addActionListener(buttonHandler);
        mainLayoutPanel.addMouseMotionListener(mouseMotionHandler);
        mainLayoutPanel.addMouseListener(mouseHandler);
        add(buttonPanel);
        setPanelEnabled(mainLayoutPanel,false);
    }

    public void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                setPanelEnabled((JPanel) component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
    }

    private void turnPlayer(){
        playerTurn.setText("Next player: "+currentPlayer.nickName);
        playerTurn.setForeground(currentPlayer.tokenColor);
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
                        var tokenElement = selectedColumnObjectHolder.findUnusedLastElement();
                        if(tokenElement!=null){
                            tokenElement.markAsUsed(currentPlayer.tokenColor);
                            if(selectedColumnObjectHolder.hasVerticalMatch(currentPlayer.tokenColor)){
                                playerTurn.setText(currentPlayer.nickName +" won!");
                                return;
                            };
                            if(columnObjectHolderList.hasConsecutiveMatch(tokenElement)){
                                playerTurn.setText(currentPlayer.nickName +" won!");
                                return;
                            };
                            if(columnObjectHolderList.hasDiagonalMatch(tokenElement)){
                                playerTurn.setText(currentPlayer.nickName +" won!");
                                return;
                            };
                            if(currentPlayer==player1){
                                currentPlayer=player2;
                            }
                            else{
                                currentPlayer=player1;
                            }
                            turnPlayer();
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
    public class ButtonHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==startBtn){
                setPanelEnabled(mainLayoutPanel,true);
            }
            if(e.getSource()==restartBtn){

            }
        }
    }
}
