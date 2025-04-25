package model;

import com.google.gson.Gson;

import view.TimerObserver;

import java.util.*;

/*
 * The creation of the board is based on the python code from the following link:
 * https://www.101computing.net/sudoku-generator-algorithm/
 * The main different from the website, outside of converting python to Java, was changing solveGrid to return a number
 * of how many solutions exist (countSolutions in this program).
 * */

public class GameBoard {
	int seconds;
    private TimerObserver timerObserver;
    private static final int SIZE = 9;
    private static final HashMap<Difficulty, Integer> DIFFICULTY_ATTEMPTS = new HashMap<>() {{
        put(Difficulty.EASY, 5);
        put(Difficulty.HARD, 15);
    }};

    private static final HashMap<Difficulty, Integer> MAX_REMOVE = new HashMap<>() {{
        put(Difficulty.EASY, 40);
        put(Difficulty.HARD, SIZE * SIZE);
    }};

    private static final HashMap<Difficulty, Integer> HINTS = new HashMap<>() {{
        put(Difficulty.EASY, 5);
        put(Difficulty.HARD, 2);
    }};

    private final GameBoardCell[][] board;
    private final Difficulty difficulty;
    private int hints;
    

    public GameBoard(Difficulty difficulty) {
    	this.seconds = 0;
        board = new GameBoardCell[SIZE][SIZE];
        this.difficulty = difficulty;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new GameBoardCell(Numbers.Empty, true);
            }
        }
        hints = HINTS.get(difficulty);

        fillBoard();
        setBoardForPlay();
        lockNumbersAfterInit();
    }

    public Numbers getValueAt(int row, int col) {
        return board[row][col].getVal();
    }

    public boolean getChangeAt(int row, int col) {
        return board[row][col].canChange();
    }

    public int getHints(){
        return hints;
    }

    public Numbers useHintAt(int row, int col) {
        hints--;
        Numbers correctNum = board[row][col].getCorrectVal();
        fillPlace(correctNum, row, col);
        return correctNum;
    }

    public boolean fillPlace(Numbers n, int row, int col) {
        if (board[row][col].getVal().equals(n) && board[row][col].getCorrectVal().equals(n)) {
            return true;
        }
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
        int attempts = DIFFICULTY_ATTEMPTS.get(this.difficulty);
        Random rnd = new Random();
        int countRemoved = 0;
        while (attempts > 0 && countRemoved < MAX_REMOVE.get(this.difficulty)) {
            int row = rnd.nextInt(SIZE);
            int col = rnd.nextInt(SIZE);
            if (!this.board[row][col].getVal().equals(Numbers.Empty)) {
                Numbers originalVal = this.board[row][col].getVal();
                this.board[row][col].setVal(Numbers.Empty);

                // we want to make sure that there is only one unique solution to the board, so if there are more, we're
                // adding back the original to the board and mark down a failed attempt
                if (this.copy().countSolutions() != 1) {
                    this.board[row][col].setVal(originalVal);
                    attempts--;
                } else {
                    countRemoved++;
                }
            }
        }
    }

    private void lockNumbersAfterInit() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j].setCanChange(board[i][j].getVal().equals(Numbers.Empty));
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

    public boolean gameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].getVal().equals(Numbers.Empty))
                    return false;
            }
        }
        return true;
    }

    public void incrementTimer() {
    	this.seconds++;
    	notifyTimer();
    }

	private void notifyTimer() {
		this.timerObserver.newTime(seconds);
	}

	public void registerTimer(TimerObserver observer) {
		this.timerObserver = observer;
	}

	public int getSeconds() {
		// TODO Auto-generated method stub
		return seconds;
	}

	public void incSecs() {
		seconds++;
		
	}
}
