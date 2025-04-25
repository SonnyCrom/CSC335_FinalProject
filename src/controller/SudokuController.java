package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class SudokuController implements ActionListener {
    private SudokuModel model;
    private Numbers currentNumber;
    private boolean isUsingHint;

    public SudokuController(Difficulty difficulty) {
        this.model = new SudokuModel(difficulty);
        setUp();
    }

    public SudokuController() {
        this.model = new SudokuModel();
        setUp();
    }

    private void setUp() {
        isUsingHint = false;
        currentNumber = Numbers.Empty;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("empty")) {
            choseNumber(Numbers.Empty);
            return;
        }
        if (command.equals("one")) {
            choseNumber(Numbers.One);
            return;
        }
        if (command.equals("two")) {
            choseNumber(Numbers.Two);
            return;
        }
        if (command.equals("three")) {
            choseNumber(Numbers.Three);
            return;
        }
        if (command.equals("four")) {
            choseNumber(Numbers.Four);
            return;
        }
        if (command.equals("five")) {
            choseNumber(Numbers.Five);
            return;
        }
        if (command.equals("six")) {
            choseNumber(Numbers.Six);
            return;
        }
        if (command.equals("seven")) {
            choseNumber(Numbers.Seven);
            return;
        }
        if (command.equals("eight")) {
            choseNumber(Numbers.Eight);
            return;
        }
        if (command.equals("nine")) {
            choseNumber(Numbers.Nine);
            return;
        }

        // Board Button Interactions
//        int x = Integer.parseInt(command.substring(0, 1));
//        int y = Integer.parseInt(command.substring(2, 3));
//        if ((x >= 0 && x < 9) && (y >= 0 && x < 9)) {
//            model.selectBoard(x, y);
//            model.gui.updateBoard();
//        }
    }

    public void addBtnObserver(NumberBtnObserver btnObserver, int row, int col) {
        this.model.registerNumberObserver(btnObserver, row, col);
    }

    public void loadBoard() {
        this.model.loadBoard();
    }

    public void setMsgObserver(MsgObserver observer) {
        this.model.setMsgObserver(observer);
    }

    private void choseNumber(Numbers num) {
        currentNumber = num;
        isUsingHint = false;
        model.choseNumber(num);
    }
}
