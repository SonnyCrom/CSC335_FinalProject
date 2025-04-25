package main;
import model.Difficulty;
import model.GameBoard;
import model.Numbers;
import view.StartScreen;
import view.SudokuGUI;

public class Main {

    public static void main(String[] args) {

        // TODO: everything here is testing, remove this
//        Numbers[][] gg = {
//                {Numbers.Eight, Numbers.One, Numbers.Three, Numbers.Two, Numbers.Seven, Numbers.Five, Numbers.Six, Numbers.Nine, Numbers.Four},
//                {Numbers.Six, Numbers.Two, Numbers.Seven, Numbers.Nine, Numbers.Four, Numbers.Eight, Numbers.Five, Numbers.One, Numbers.Three},
//                {Numbers.Five, Numbers.Nine, Numbers.Four, Numbers.Six, Numbers.Three, Numbers.One, Numbers.Eight, Numbers.Seven, Numbers.Two},
//                {Numbers.One, Numbers.Seven, Numbers.Five, Numbers.Eight, Numbers.Two, Numbers.Three, Numbers.Four, Numbers.Six, Numbers.Nine},
//                {Numbers.Three, Numbers.Six, Numbers.Nine, Numbers.Seven, Numbers.Five, Numbers.Four, Numbers.Two, Numbers.Eight, Numbers.One},
//                {Numbers.Two, Numbers.Four, Numbers.Eight, Numbers.One, Numbers.Six, Numbers.Nine, Numbers.Seven, Numbers.Three, Numbers.Five},
//                {Numbers.Four, Numbers.Five, Numbers.One, Numbers.Three, Numbers.Eight, Numbers.Seven, Numbers.Nine, Numbers.Two, Numbers.Six},
//                {Numbers.Nine, Numbers.Eight, Numbers.Two, Numbers.Five, Numbers.One, Numbers.Six, Numbers.Three, Numbers.Four, Numbers.Seven},
//                {Numbers.Seven, Numbers.Three, Numbers.Six, Numbers.Four, Numbers.Nine, Numbers.Two, Numbers.One, Numbers.Five, Numbers.Eight}
//        };
    	
		//StartScreen view = new StartScreen();
    	SudokuGUI gameView = new SudokuGUI();
    	
    	
        for (int k = 0; k <0; k++) {
            GameBoard g = new GameBoard(Difficulty.HARD);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    Numbers val = g.getValueAt(i, j);
                    if (val == Numbers.Empty) {
                        System.out.print("X|");
                    } else {
                        System.out.print(val.ordinal() + 1 + "|");
                    }
                }
                System.out.println();
                System.out.println("------------------");
            }
        }

    }
}

