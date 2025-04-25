package view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.ModuleLayer.Controller;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.NumRep;
import model.Numbers;
import model.Difficulty;
import model.GameBoard;
import model.Model;
import model.NumRep;
import controller.SudokuController;

public class SudokuGUI extends JFrame {

    private NumRep gridRep;
    private NumbersGUI numRep;
    private ValidGUI validGui;
    private JPanel panel;
    private SudokuController controller;
    private int SIZE = 9;

//	public SudokuGUI() {
//		this.controller = new SudokuController(new Model());
//		this.setTitle("Sudoku!");
//		this.setSize(500,500);
//		this.setUp();
//	}

    public SudokuGUI(Model model) {
        this.controller = new SudokuController(model);
        this.setTitle("Sudoku!");
        this.setSize(600, 500);
        this.setMinimumSize(new Dimension(600, 500));
        this.setUp();
    }

    private void setUp() {
        addGameBoard();
        numRep = new NumbersGUI();
        validGui = new ValidGUI();
        controller.addObserver(numRep);
        this.add(validGui, BorderLayout.EAST);
        controller.addValid(validGui);
        this.add(numRep);
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
        JButton hint = new JButton("hint");
        hint.setActionCommand("hint");
        hint.addActionListener(controller);
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
                NumberBtn btn = new NumberBtn(Color.green);
                btn.setActionCommand(Integer.toString(i) + " " + Integer.toString(j));
                btn.addActionListener(controller);
                controller.addBtnObserver(btn, i, j);
                boardPanel.add(btn);
            }
        }
    }

    public void updateBoard() {
//        JPanel boardPanel = new JPanel();
//        boardPanel.setLayout(new GridLayout(SIZE, SIZE, 10, 10));
//        this.add(boardPanel, BorderLayout.NORTH);
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                JButton TEST = new JButton(" ");
//                TEST.setActionCommand(Integer.toString(i) + " " + Integer.toString(j));
//                TEST.addActionListener(controller);
//                boardPanel.add(TEST);
//                Numbers num = controller.getBoard().getValueAt(i, j);
//                if (num == Numbers.Empty) {
//                    TEST.setText(" ");
//                } else {
//                    TEST.setText(Integer.toString(num.toInteger()));
//                }
//            }
//        }

    }
}