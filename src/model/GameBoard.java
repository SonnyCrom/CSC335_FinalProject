package model;

import com.google.gson.Gson;


import java.util.*;

/*
 * The creation of the board is based on the python code from the following link:
 * https://www.101computing.net/sudoku-generator-algorithm/
 * The main different from the website, outside of converting python to Java, was changing solveGrid to return a number
 * of how many solutions exist (countSolutions in this program).
 * */

public class GameBoard {
    private static final int SIZE = 9;
    private static final HashMap<Difficulty, Integer> DIFFICULTY_ATTEMTS = new HashMap<>() {{
        put(Difficulty.EASY, 5);
        put(Difficulty.HARD, 15);
    }};

    private static final HashMap<Difficulty, Integer> MAX_REMOVE = new HashMap<>() {{
        put(Difficulty.EASY, 40);
        put(Difficulty.HARD, SIZE * SIZE);
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

        fillBoard();
        setBoardForPlay();
        lockNumbersAfterInit();
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
            int solutions = c.countSolutions();
            if (solutions > 0) {
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
        int attemts = DIFFICULTY_ATTEMTS.get(this.difficulty);
        Random rnd = new Random();
        int countRemoved = 0;
        while (attemts > 0 && countRemoved < MAX_REMOVE.get(this.difficulty)) {
            int row = rnd.nextInt(SIZE);
            int col = rnd.nextInt(SIZE);
            if (!this.board[row][col].getVal().equals(Numbers.Empty)) {
                Numbers originalVal = this.board[row][col].getVal();
                this.board[row][col].setVal(Numbers.Empty);

                // we want to make sure that there is only one unique solution to the board, so if there are more, we're
                // adding back the original to the board and mark down a failed attempt
                if (this.copy().countSolutions() != 1) {
                    this.board[row][col].setVal(originalVal);
                    attemts--;
                } else {
                    countRemoved++;
                }
            }
        }
    }

    private void lockNumbersAfterInit() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!board[i][j].getVal().equals(Numbers.Empty)) {
                    board[i][j].setCanChange(true);
                } else {
                    board[i][j].setCanChange(false);
                }
            }
        }
    }

    private List<int[]> getAllCells() {
        List<int[]> cells = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells.add(new int[]{i, j});
            }
        }
        return cells;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.board[i][j].getVal().equals(Numbers.Empty)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean fillBoard() {
        List<int[]> cells = getAllCells();

        for (int[] cell : cells) {
            if (this.board[cell[0]][cell[1]].getVal().equals(Numbers.Empty)) {
                List<Numbers> numsList = Arrays.asList(Numbers.values());
                Collections.shuffle(numsList);

                for (Numbers n : numsList) {
                    if (!n.equals(Numbers.Empty) && isValidPlacement(n, cell[0], cell[1])) {
                        this.board[cell[0]][cell[1]].setVal(n);
                        this.board[cell[0]][cell[1]].setCorrectVal(n);
                        if (isBoardFull() || fillBoard()) {
                            return true;
                        }
                    }
                }
                this.board[cell[0]][cell[1]].setVal(Numbers.Empty);
                this.board[cell[0]][cell[1]].setCorrectVal(Numbers.Empty);
                break;
            }
        }

        return isBoardFull();
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

    private int countSolutions() {
        return countSolutionsHelper(0, 0);
    }

    private int countSolutionsHelper(int row, int col) {
        if (row == SIZE) {
            return 1;
        }

        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col + 1) % SIZE;

        if (!board[row][col].getVal().equals(Numbers.Empty)) {
            return countSolutionsHelper(nextRow, nextCol);
        }

        int count = 0;
        for (int num = 0; num < Numbers.values().length - 1; num++) {
            Numbers numEnum = Numbers.values()[num];
            if (isValidPlacement(numEnum, row, col)) {
                board[row][col].setVal(numEnum);
                count += countSolutionsHelper(nextRow, nextCol);
                board[row][col].setVal(Numbers.Empty);
            }
        }

        return count;
    }

    private GameBoard solve() {
        GameBoard solution = this.copy();
        if (solution.solveHelper(0, 0)) {
            return solution;
        } else {
            return null;
        }
    }

    private boolean solveHelper(int row, int col) {
        if (row == SIZE)
            return true;
        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col + 1) % SIZE;
        if (!board[row][col].getVal().equals(Numbers.Empty)) {
            return solveHelper(nextRow, nextCol);
        }

        for (int n = 0; n < Numbers.values().length - 1; n++) {
            Numbers numEnum = Numbers.values()[n];
            if (isValidPlacement(numEnum, row, col)) {
                board[row][col].setVal(numEnum);
                if (solveHelper(nextRow, nextCol)) {
                    return true;
                }
                board[row][col].setVal(Numbers.Empty);
            }
        }

        return false;
    }

    // how a user adds a number to a board, doesn't check if the move is a good one
    public boolean addNumber(Numbers n, int row, int col) {
        if (board[row][col].canChange()) {
            board[row][col].setVal(n);
            return true;
        }
        return false;
    }

    // how a user removes a number
    public boolean removeNumber(int row, int col) {
        if (board[row][col].canChange()) {
            board[row][col].setVal(Numbers.Empty);
            return true;
        }
        return false;
    }

    // fills a single cell with a correct solution
    public void giveHint(int row, int col) {
        if (board[row][col].canChange()) {
            GameBoard solution = this.solve();
            if (solution != null) {
                board[row][col].setVal(solution.getValueAt(row, col));
            }
        }
    }


    public boolean gameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].getVal().equals(Numbers.Empty))
                    return false;
            }
        }
        return true;
    }
}
