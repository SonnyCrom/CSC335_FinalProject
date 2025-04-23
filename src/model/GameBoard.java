package model;

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
        if (board[row][col].isCanChange() &&
                (n == Numbers.Empty || isValidPlacement(n, row, col))) {
            board[row][col].setVal(n);
            return true;
        }
        return false;
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

}
