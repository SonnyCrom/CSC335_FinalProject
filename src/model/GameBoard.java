package model;

import com.google.gson.Gson;

private static final int SIZE = 9;

public class GameBoard {
    private GameBoardCell[][] board;

    public GameBoard(int[][] initialValues) {
        board = new GameBoardCell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (initialValues[i][j] == 0) {
                    board[i][j] = new GameBoardCell(Numbers.Empty, true);
                } else {
                    board[i][j] = new GameBoardCell(Numbers.values()[initialValues[i][j] - 1], true);
                }
            }
        }
    }

    public boolean fillPlace(Numbers n, int row, int col) {
        if (board[row][col].canChange() &&
                (n == Numbers.Empty || isValidPlacement(n, row, col))) {
            GameBoard c = this.copy();
            c.board[row][col].setVal(n);
            return true;
        }
        return false;
    }

    public GameBoard copy() {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(this), getClass());
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
                    for (int num = 1; num < Numbers.values().length; num++) {
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
    }
}
