package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameBoard;
import model.Model;
import view.BoardObserver;
import view.NumberBtnObserver;
import view.Observer;
import view.ValidGUI;

public class SudokuController implements ActionListener {
    private Model model;

    public SudokuController(Model model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("empty")) {
            model.changeNum(0);
            return;
        }
        if (command.equals("one")) {
            model.changeNum(1);
            return;
        }
        if (command.equals("two")) {
            model.changeNum(2);
            return;
        }
        if (command.equals("three")) {
            model.changeNum(3);
            return;
        }
        if (command.equals("four")) {
            model.changeNum(4);
            return;
        }
        if (command.equals("five")) {
            model.changeNum(5);
            return;
        }
        if (command.equals("six")) {
            model.changeNum(6);
            return;
        }
        if (command.equals("seven")) {
            model.changeNum(7);
            return;
        }
        if (command.equals("eight")) {
            model.changeNum(8);
            return;
        }
        if (command.equals("nine")) {
            model.changeNum(9);
            return;
        }


        if (command.equals("start")) {
            model.startGame(null);
            return;
        }
        if (command.equals("Quit")) {
            System.exit(0);
        }

        // Board Button Interactions
        int x = Integer.parseInt(command.substring(0, 1));
        int y = Integer.parseInt(command.substring(2, 3));
        if ((x >= 0 && x < 9) && (y >= 0 && x < 9)) {
            model.selectBoard(x, y);
            model.gui.updateBoard();
        }
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }

    public void addBObserver(BoardObserver observer) {
        model.registerBObserver(observer);
    }

    public GameBoard getBoard() {
        return model.getBoard();
    }

    public void addValid(ValidGUI validGui) {
        model.setValid(validGui);
    }

    public void addBtnObserver(NumberBtnObserver btnObserver, int row, int col) {
        this.model.registerNumberObserver(btnObserver, row, col);
    }

    public void loadBoard(){
        this.model.loadBoard();
    }
}
