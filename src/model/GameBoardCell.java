package model;


public class GameBoardCell {
    private Numbers val;
    private Numbers correctVal;
    private boolean canChange;

    public GameBoardCell(Numbers val, boolean canChange) {
        this.val = val;
        this.canChange = canChange;
        this.correctVal = Numbers.Empty;
    }

    public Numbers getVal() {
        return val;
    }

    public void setVal(Numbers val) {
        if (canChange) {
            this.val = val;
        }
    }

    public boolean canChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange) {
        this.canChange = canChange;
    }

    public void setCorrectVal(Numbers correctVal) {
        this.correctVal = correctVal;
    }

    public Numbers getCorrectVal() {
        return correctVal;
    }
}
