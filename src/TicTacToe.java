import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {

    boolean playerOneTurn;
    JButton newGame, gesture, speech, quit;
    JButton[][] board = new JButton[3][3];
    JLabel status;
    int counter;

    public TicTacToe() {

        // Window - frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        frame.getContentPane().setBackground(new Color(25,25,25));
        frame.setLayout(new BorderLayout());

        // Game-status/ title panel
        status = new JLabel();
        status.setText("Tic Tac Toe");
        status.setFont(new Font("Bad Script", Font.BOLD,45));
        status.setForeground(Color.WHITE);
        status.setHorizontalTextPosition(JLabel.CENTER);
        status.setVerticalTextPosition(JLabel.TOP);

        JPanel statusPanel = new JPanel();
        statusPanel.setBounds(0,0,400,100);
        statusPanel.add(status);
        statusPanel.setBackground(new Color(25,25,25));

        // Left panel - visual border - no function
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(25,25,25));
        leftPanel.setPreferredSize(new Dimension(25,550));

        // Right panel - visual border - no function
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(25,25,25));
        rightPanel.setPreferredSize(new Dimension(25,550));

        // Container panel for Board and Options
        JPanel containerPanel = new JPanel();
        containerPanel.setBackground(new Color(25,25,25));
        containerPanel.setPreferredSize(new Dimension(400,575));

        // Game panel: Board
        JPanel tttPanel = new JPanel();
        tttPanel.setPreferredSize(new Dimension(300,300));
        tttPanel.setBackground(new Color(25,25,25));
        tttPanel.setLayout(new GridLayout(3,3));

        // Board-Matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton();
                board[i][j].setFont(new Font("Bad Script", Font.BOLD,55));
                board[i][j].addActionListener(this);
                board[i][j].setBackground(Color.WHITE);
                board[i][j].setFocusable(false);
                board[i][j].setEnabled(false);
                tttPanel.add(board[i][j]);
            }
        }

        // deactivated board: default-decoration
        board[0][0].setText("T");
        board[0][1].setText("I");
        board[0][2].setText("C");
        board[1][0].setText("T");
        board[1][1].setText("A");
        board[1][2].setText("C");
        board[2][0].setText("T");
        board[2][1].setText("O");
        board[2][2].setText("E");

        // Panel for Options: Start Game, Speech Recognition and Quit Game
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(25,25,25));
        bottomPanel.setPreferredSize(new Dimension(300,250));
        bottomPanel.setLayout(new GridLayout(4,1));
        bottomPanel.setBorder(new EmptyBorder(25, 0, 0, 0));

        newGame = new JButton();
        newGame.setFont(new Font("Bad Script", Font.BOLD,25));
        newGame.setText("New Game");
        newGame.setFocusable(false);
        newGame.setBackground(Color.WHITE);
        newGame.addActionListener(this);
        bottomPanel.add(newGame);

        gesture = new JButton();
        gesture.setFont(new Font("Bad Script", Font.BOLD,25));
        gesture.setText("Gesture Control: OFF");
        gesture.setFocusable(false);
        gesture.setBackground(Color.WHITE);
        gesture.addActionListener(this);
        bottomPanel.add(gesture);

        speech = new JButton();
        speech.setFont(new Font("Bad Script", Font.BOLD,25));
        speech.setText("Speech Recognition: OFF");
        speech.setFocusable(false);
        speech.setBackground(Color.WHITE);
        speech.addActionListener(this);
        bottomPanel.add(speech);

        quit = new JButton();
        quit.setFont(new Font("Bad Script", Font.BOLD,25));
        quit.setText("Quit Game");
        quit.setFocusable(false);
        quit.setBackground(Color.WHITE);
        quit.addActionListener(this);
        bottomPanel.add(quit);

        // South Panel - visual border - no function
        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(25,25,25));
        southPanel.setPreferredSize(new Dimension(400,25));

        // Frame and Content-Positioning
        containerPanel.add(tttPanel, BorderLayout.NORTH);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(containerPanel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // EXIT GAME
        if (e.getSource() == quit) {
            System.exit(0);
        }
        // NEW GAME - RANDOM PLAYER STARTS - ACTIVATE BOARD
        if (e.getSource() == newGame) {
            counter = 0;
            playerOneTurn = getRandomZeroOrOne() == 0;

            if (playerOneTurn) {
                status.setText("Ready Player One");
            } else {
                status.setText("Ready Player Two");
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j].setEnabled(true);
                    board[i][j].setText("");
                }
            }
        }

        // SPEECH RECOGNITION FEATURE
        if (e.getSource() == speech) {

            if (speech.getText().equals("Speech Recognition: OFF")) {
                speech.setText("Speech Recognition: ON");
                // DO SPEECH RECOGNITION HERE
            } else {
                speech.setText("Speech Recognition: OFF");
                // DEACTIVATE SPEECH RECOGNITION HERE
            }
        }

        // GESTURE CONTROL
        if (e.getSource() == gesture) {

            if (gesture.getText().equals("Gesture Control: OFF")) {
                gesture.setText("Gesture Control: ON");
                // DO GESTURE CONTROL HERE
            } else {
                gesture.setText("Gesture Control: OFF");
                // DEACTIVATE GESTURE CONTROL HERE
            }
        }

        // NINE CASES OF DRAWING X and O PLUS NEXT TURN UPDATE
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (e.getSource() == board[i][j] && board[i][j].getText().equals("")) {
                    if (playerOneTurn) {
                        board[i][j].setText("X");
                    } else {
                        board[i][j].setText("O");
                    }
                    counter++;
                    nextTurn();
                    check();

                }
            }
        }
    }
    // ROLL THE DICES - WHO STARTS?
    public int getRandomZeroOrOne(){
        return (int) Math.round(Math.random());
    }

    /* CHECK FOR WIN CONDITIONS OR DRAW
       ---MATRIX---
       00 | 01 | 02
       10 | 11 | 12
       20 | 21 | 22
       ------------
     */
    public void check() {

        if ((
                board[0][0].getText().equals("X") &&
                board[0][1].getText().equals("X") &&
                board[0][2].getText().equals("X")) ||
            (
                board[1][0].getText().equals("X") &&
                board[1][1].getText().equals("X") &&
                board[1][2].getText().equals("X")) ||
            (
                board[2][0].getText().equals("X") &&
                board[2][1].getText().equals("X") &&
                board[2][2].getText().equals("X")) ||
            (
                board[0][0].getText().equals("X") &&
                board[1][0].getText().equals("X") &&
                board[2][0].getText().equals("X")) ||
            (
                board[0][0].getText().equals("X") &&
                board[1][1].getText().equals("X") &&
                board[2][2].getText().equals("X")) ||
            (
                board[0][1].getText().equals("X") &&
                board[1][1].getText().equals("X") &&
                board[2][1].getText().equals("X")) ||
            (
                board[2][0].getText().equals("X") &&
                board[1][1].getText().equals("X") &&
                board[0][2].getText().equals("X")) ||
            (
                board[0][2].getText().equals("X") &&
                board[1][2].getText().equals("X") &&
                board[2][2].getText().equals("X"))) {

                    status.setText("Player One Wins!");
                    disableBoard();
        } else if ((
                board[0][0].getText().equals("O") &&
                board[0][1].getText().equals("O") &&
                board[0][2].getText().equals("O")) ||
            (
                board[1][0].getText().equals("O") &&
                board[1][1].getText().equals("O") &&
                board[1][2].getText().equals("O")) ||
            (
                board[2][0].getText().equals("O") &&
                board[2][1].getText().equals("O") &&
                board[2][2].getText().equals("O")) ||
            (
                board[0][0].getText().equals("O") &&
                board[1][0].getText().equals("O") &&
                board[2][0].getText().equals("O")) ||
            (
                board[0][0].getText().equals("O") &&
                board[1][1].getText().equals("O") &&
                board[2][2].getText().equals("O")) ||
            (
                board[0][1].getText().equals("O") &&
                board[1][1].getText().equals("O") &&
                board[2][1].getText().equals("O")) ||
            (
                board[2][0].getText().equals("O") &&
                board[1][1].getText().equals("O") &&
                board[0][2].getText().equals("O")) ||
            (
                board[0][2].getText().equals("O") &&
                board[1][2].getText().equals("O") &&
                board[2][2].getText().equals("O"))) {

            status.setText("Player Two Wins!");
            disableBoard();
        } else if (counter > 8) {
            status.setText("Draw!");
            disableBoard();
        }
    }

    public void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setEnabled(false);
            }
        }
    }

    public void nextTurn() {
        if (playerOneTurn) {
            status.setText("Player Two's Turn!");
            playerOneTurn = false;
        } else {
            status.setText("Player One's Turn!");
            playerOneTurn = true;
        }
    }
}