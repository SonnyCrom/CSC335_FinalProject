package model;

public class GameBoardCell {
    private Numbers val;
    private boolean canChange;

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

    public boolean canChange() {
        return canChange;
    }

    public void setCanChange(boolean canChange){
        this.canChange = canChange;
    }
}
