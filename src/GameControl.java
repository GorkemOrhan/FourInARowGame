import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Shape.JFrameTokenShape;

public class GameControl extends JFrame {

    public JPanel mainLayoutPanel;
    public JPanel buttonPanel;
    public ColumnObjectHolderArrayList columnObjectHolderList;
    private JLabel playerTurn;
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public JButton startBtn = new JButton("Start");
    public JButton restartBtn = new JButton("Restart");

    public boolean isStarted;
    public GameControl() {
        initGame();
    }

    public void initGame() {
        this.getContentPane().removeAll();
        playerTurn = new JLabel();
        mainLayoutPanel = new JPanel(new GridLayout(GameSettings.ROWS, GameSettings.COLUMNS));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainLayoutPanel.setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((int) GameSettings.SCREEN_SIZE.getX(), (int) GameSettings.SCREEN_SIZE.getY());
        columnObjectHolderList = new ColumnObjectHolderArrayList();

        for (var i = 0; i < GameSettings.COLUMNS; i++) {
            ColumnObjectHolder columnObjectHolder = new ColumnObjectHolder(i);
            columnObjectHolderList.add(columnObjectHolder);
        }

        for (int i = 0; i < GameSettings.ROWS; i++) {
            for (int j = 0; j < GameSettings.COLUMNS; j++) {
                var columnObjectHolder = columnObjectHolderList.get(j);
                JFrameTokenShape shape = columnObjectHolder.addTokenShape(j, i);
                mainLayoutPanel.add(shape);
            }
        }

        add(mainLayoutPanel, BorderLayout.SOUTH);
        playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
        playerTurn.setFont(new Font("Verdana", Font.PLAIN, 15));
        buttonPanel.add(startBtn);
        buttonPanel.add(playerTurn);
        buttonPanel.add(restartBtn);
        MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();
        MouseHandler mouseHandler = new MouseHandler();
        ButtonHandler buttonHandler = new ButtonHandler();
        startBtn.addActionListener(buttonHandler);
        restartBtn.addActionListener(buttonHandler);
        mainLayoutPanel.addMouseMotionListener(mouseMotionHandler);
        mainLayoutPanel.addMouseListener(mouseHandler);
        add(buttonPanel);
        this.setVisible(true);
        isStarted = false;
        setPointForColumnObjectHolder();
    }

    public void setPointForColumnObjectHolder(){
        var mainLayoutPanelWidth = this.mainLayoutPanel.getSize().width;
        var tokenShapeWidth = mainLayoutPanelWidth / GameSettings.COLUMNS;

        for (var i = 0; i < this.columnObjectHolderList.size(); i++) {
            var mostLeftX = tokenShapeWidth * i;
            var mostRightX = mostLeftX + tokenShapeWidth;
            this.columnObjectHolderList.get(i).setPoint(mostLeftX, mostRightX);
        }
    }

    public void startGame() {
        setPlayers();
        turnPlayer();
        isStarted = true;
    }

    public void restartGame() {
        getContentPane().remove(mainLayoutPanel);
        getContentPane().remove(buttonPanel);
        initGame();
        mainLayoutPanel.repaint();
        buttonPanel.repaint();
        revalidate();
        startGame();
    }

    private void setPlayers(){
        player1 = new Player(Color.BLUE, "First");
        player2 = new Player(Color.ORANGE, "Second");
        currentPlayer = player1;
    }

    private void turnPlayer() {
        playerTurn.setText("Next player: " + currentPlayer.nickName);
        playerTurn.setForeground(currentPlayer.tokenColor);
    }

    public class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(!isStarted) return;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    int mouseX = e.getX();
                    for (ColumnObjectHolder columnObjectHolder : columnObjectHolderList) {
                        if (mouseX >= columnObjectHolder.mostLeftPoint && mouseX <= columnObjectHolder.mostRightPoint) {
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

    public void finishGame(){
        playerTurn.setText(currentPlayer.nickName + " player won!");
        isStarted = false;
    }

    public class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(!isStarted) return;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    int mouseX = e.getX();
                    ColumnObjectHolder selectedColumnObjectHolder = null;
                    for (ColumnObjectHolder columnObjectHolder : columnObjectHolderList) {
                        if (mouseX >= columnObjectHolder.mostLeftPoint && mouseX <= columnObjectHolder.mostRightPoint) {
                            selectedColumnObjectHolder = columnObjectHolder;
                            break;
                        }
                    }

                    if (selectedColumnObjectHolder != null) {
                        var tokenElement = selectedColumnObjectHolder.findUnusedLastElement();
                        if (tokenElement != null) {
                            tokenElement.markAsUsed(currentPlayer.tokenColor);
                            if (selectedColumnObjectHolder.hasVerticalMatch(currentPlayer.tokenColor)) {
                                finishGame();
                                return;
                            }
                            ;
                            if (columnObjectHolderList.hasConsecutiveMatch(tokenElement)) {
                                finishGame();
                                return;
                            }
                            ;
                            if (columnObjectHolderList.hasDiagonalMatch(tokenElement)) {
                                finishGame();
                                return;
                            }
                            ;
                            if (currentPlayer == player1) {
                                currentPlayer = player2;
                            } else {
                                currentPlayer = player1;
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

    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn) {
                startGame();
                startBtn.setEnabled(false);
            } else if (e.getSource() == restartBtn) {
                restartGame();
            }
        }
    }
}
