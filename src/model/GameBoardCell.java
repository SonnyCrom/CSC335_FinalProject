package model;

public class GameBoardCell {
    private Numbers val;
    private final boolean canChange;

    public GameBoardCell(Numbers val, boolean canChange) {
        this.val = val;
        this.canChange = canChange;
    }

    public Numbers getVal() {
        return val;
    }

    public void setVal(Numbers val) {
        if (canChange) {
            this.val = val;
        }
    }

    public boolean isCanChange() {
        return canChange;
    }
}
