package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class SudokuController implements ActionListener {
    private SudokuModel model;

    public SudokuController(Difficulty difficulty) {
        this.model = new SudokuModel(difficulty);
    }

    public SudokuController() {
        this.model = new SudokuModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("empty")) {
            model.choseNumber(Numbers.Empty);
            return;
        }
        if (command.equals("one")) {
            model.choseNumber(Numbers.One);
            return;
        }
        if (command.equals("two")) {
            model.choseNumber(Numbers.Two);
            return;
        }
        if (command.equals("three")) {
            model.choseNumber(Numbers.Three);
            return;
        }
        if (command.equals("four")) {
            model.choseNumber(Numbers.Four);
            return;
        }
        if (command.equals("five")) {
            model.choseNumber(Numbers.Five);
            return;
        }
        if (command.equals("six")) {
            model.choseNumber(Numbers.Six);
            return;
        }
        if (command.equals("seven")) {
            model.choseNumber(Numbers.Seven);
            return;
        }
        if (command.equals("eight")) {
            model.choseNumber(Numbers.Eight);
            return;
        }
        if (command.equals("nine")) {
            model.choseNumber(Numbers.Nine);
            return;
        }
        if (command.equals("hint")) {
            model.choseHint();
            return;
        }
    }

    public void addBtnObserver(NumberBtnObserver btnObserver, int row, int col) {
        this.model.registerNumberObserver(btnObserver, row, col);
    }

    public void loadBoard() {
        this.model.loadBoard();
    }

    public void setMsgObserver(MsgObserver observer) {
        this.model.setMsgObserver(observer);
        model.choseNumber(Numbers.Empty);
    }
}
