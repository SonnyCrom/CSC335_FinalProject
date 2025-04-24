package model;

import com.google.gson.Gson;

import java.util.*;

public class GameBoard {
    private static final int SIZE = 9;
    private static final HashMap<Difficulty, Integer> DIFFICULTY_ADD = new HashMap<>() {{
        put(Difficulty.EASY, 55);
        put(Difficulty.HARD, 40);
    }};

    private final GameBoardCell[][] board;
    private final Difficulty difficulty;

    public GameBoard(Difficulty difficulty) {
        board = new GameBoardCell[SIZE][SIZE];
        this.difficulty = difficulty;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new GameBoardCell(Numbers.Empty, true);
            }
        }
        setBoardForPlay();
    }

    public Numbers getValueAt(int row, int col) {
        return board[row][col].getVal();
    }

    public boolean fillPlace(Numbers n, int row, int col) {
        if (board[row][col].canChange() &&
                (n == Numbers.Empty || isValidPlacement(n, row, col))) {
            GameBoard c = this.copy();
            c.board[row][col].setVal(n);
            // make sure the board is winnable with this value
            if (c.solve()) {
                this.board[row][col].setVal(n);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public GameBoard copy() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), getClass());
    }

    private void setBoardForPlay() {
        Random random = new Random();
        for (int i = 0; i < DIFFICULTY_ADD.get(this.difficulty); i++) {
            boolean isAdded = false;

            // keep trying until a cell was added
            while (!isAdded) {
                int row = random.nextInt(SIZE);
                int col = random.nextInt(SIZE);
                if (board[row][col].getVal().equals(Numbers.Empty)) {
                    List<Numbers> numsList = Arrays.asList(Numbers.values());
                    Collections.shuffle(numsList);

                    for (Numbers n : numsList) {
                        if (!n.equals(Numbers.Empty) && fillPlace(n, row, col)) {
                            isAdded = true;
                            break;
                        }
                    }
                }
            }
        }

        // set all the current non-empty values as non-changeable
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!board[i][j].getVal().equals(Numbers.Empty)) {
                    board[i][j].setCanChange(false);
                }
            }
        }
    }

    private boolean isNumberInRow(Numbers n, int row) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i].getVal() == n) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInCol(Numbers n, int col) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col].getVal() == n) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(Numbers n, int row, int col) {
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j].getVal() == n) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPlacement(Numbers n, int row, int col) {
        return !isNumberInBox(n, row, col) && !isNumberInRow(n, row) && !isNumberInCol(n, col);
    }

    private boolean solve() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].getVal().equals(Numbers.Empty)) {
                    for (int num = 0; num < Numbers.values().length - 1; num++) {
                        Numbers numEnum = Numbers.values()[num];
                        if (isValidPlacement(numEnum, i, j)) {
                            board[i][j].setVal(numEnum);

                            if (this.solve()) {
                                return true;
                            }
                            // if we were not able to solve the game with this placement, reset spot and try another number
                            else {
                                board[i][j].setVal(Numbers.Empty);
                            }
                        }
                    }

                    // we try to fill that spot with every number and all failed
                    return false;
                }
            }
        }

        return true;
    }
}
