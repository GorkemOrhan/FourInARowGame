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
    public ColumnObjectHolderArrayList columnObjectHolderList;
    private JLabel playerTurn;
    public Player player1;
    public Player player2;
    public Player currentPlayer;

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
        add(playerTurn);
        MouseMotionHandler mouseMotionHandler =new MouseMotionHandler();
        MouseHandler mouseHandler =new MouseHandler();
        mainLayoutPanel.addMouseMotionListener(mouseMotionHandler);
        mainLayoutPanel.addMouseListener(mouseHandler);
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
                            if(selectedColumnObjectHolder.findFourInAColumn(currentPlayer.tokenColor)){
                                playerTurn.setText(currentPlayer.nickName +" won!");
                                return;
                            };
                            if(columnObjectHolderList.hasConsecutiveSameElements(tokenElement)){
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
}
