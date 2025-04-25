package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Difficulty;
import controller.SudokuController;

public class SudokuGUI extends JFrame {

    private final SudokuController controller;
    private final int SIZE = 9;

    private final HashMap<Integer, HashMap<Integer, Color>> colorMap = new HashMap<>() {
        {
            put(0, new HashMap<>() {
                {
                    put(0, Color.green);
                    put(1, new Color(255, 128, 128));
                    put(2, Color.cyan);
                }
            });
            put(1, new HashMap<>() {
                {
                    put(0, new Color(255, 128, 255));
                    put(1, Color.yellow);
                    put(2, Color.lightGray);
                }
            });
            put(2, new HashMap<>() {
                {
                    put(0, Color.pink);
                    put(1, Color.gray);
                    put(2, Color.white);
                }
            });
        }
    };


    public SudokuGUI(Difficulty difficulty) {
        this.controller = new SudokuController(difficulty);
        this.setTitle("Sudoku!");
        this.setSize(600, 700);
        this.setMinimumSize(new Dimension(600, 700));
        this.setUp();
        this.controller.loadBoard();
    }

    public SudokuGUI() {
        this.controller = new SudokuController();
        this.setTitle("Sudoku!");
        this.setSize(600, 700);
        this.setMinimumSize(new Dimension(600, 700));
        this.setUp();
        this.controller.loadBoard();
    }


    private void setUp() {
        addGameBoard();
        MsgLabel msgLabel = new MsgLabel();
        controller.setMsgObserver(msgLabel);
        this.add(msgLabel);

        //

        //setting up the main panel
        JPanel mainPanel = new JPanel();
        this.add(mainPanel, BorderLayout.SOUTH);

        //setting up the switch button
        JButton empty = new JButton("  ");
        empty.setActionCommand("empty");
        empty.addActionListener(controller);
        mainPanel.add(empty);

        //setting up the switch button
        JButton one = new JButton("1");
        one.setActionCommand("one");
        one.addActionListener(controller);
        mainPanel.add(one);

        //setting up the switch button
        JButton two = new JButton("2");
        two.setActionCommand("two");
        two.addActionListener(controller);
        mainPanel.add(two);

        //setting up the switch button
        JButton three = new JButton("3");
        three.setActionCommand("three");
        three.addActionListener(controller);
        mainPanel.add(three);

        //setting up the switch button
        JButton four = new JButton("4");
        four.setActionCommand("four");
        four.addActionListener(controller);
        mainPanel.add(four);

        //setting up the switch button
        JButton five = new JButton("5");
        five.setActionCommand("five");
        five.addActionListener(controller);
        mainPanel.add(five);

        //setting up the switch button
        JButton six = new JButton("6");
        six.setActionCommand("six");
        six.addActionListener(controller);
        mainPanel.add(six);

        //setting up the switch button
        JButton seven = new JButton("7");
        seven.setActionCommand("seven");
        seven.addActionListener(controller);
        mainPanel.add(seven);

        //setting up the switch button
        JButton eight = new JButton("8");
        eight.setActionCommand("eight");
        eight.addActionListener(controller);
        mainPanel.add(eight);

        //setting up the switch button
        JButton nine = new JButton("9");
        nine.setActionCommand("nine");
        nine.addActionListener(controller);
        mainPanel.add(nine);

        //
        HintBtn hint = new HintBtn();
        hint.setActionCommand("hint");
        hint.addActionListener(controller);
        controller.setHintObserver(hint);
        mainPanel.add(hint);


        //adding a window listener for closing the app
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    private void addGameBoard() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(SIZE, SIZE, 10, 10));
        this.add(boardPanel, BorderLayout.NORTH);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                NumberBtn btn = new NumberBtn(colorMap.get(i / 3).get(j / 3));
                btn.setActionCommand("Cell " + i + " " + j);
                btn.addActionListener(controller);
                controller.addBtnObserver(btn, i, j);
                boardPanel.add(btn);
            }
        }
    }
}