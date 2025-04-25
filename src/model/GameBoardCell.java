package model;

import java.util.Objects;

public class GameBoardCell {
    private Numbers val;
    private boolean canChange;
    private int row;
    private int col;

    public GameBoardCell(Numbers val, boolean canChange, int row, int col){
        this.val = val;
        this.canChange = canChange;
        this.row = row;
        this.col = col;
    }

    public GameBoardCell(GameBoardCell other) {
    	this.val = other.getVal();
    	this.canChange = other.canChange();
    	this.row = other.getRow();
    	this.col = other.getCol();
    }
    
    public int getCol() {
    	return this.col;
	}

	public int getRow() {
		return this.row;
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
    
    public GameBoardCell copy() {
    	return new GameBoardCell(this);
    }

	@Override
	public int hashCode() {
		return Objects.hash(canChange, col, row, val);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameBoardCell other = (GameBoardCell) obj;
		return canChange == other.canChange && col == other.col && row == other.row && val == other.val;
	}
}
