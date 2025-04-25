package model;

import view.Observer;

public enum Numbers{
    One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Empty;
    
    public int toInteger() {
    	if(this == Empty)
    		return 0;
    	else
    		return this.ordinal() + 1;
    }
}
