package model;

import view.Observer;

public enum Numbers {
    One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Empty;

    public int toInteger() {
        if (this == Empty)
            return 0;
        else
            return this.ordinal() + 1;
    }

    public Numbers fromInteger(int i) {
        if (i == 1)
            return One;
        if (i == 2)
            return Two;
        if (i == 3)
            return Three;
        if (i == 4)
            return Four;
        if (i == 5)
            return Five;
        if (i == 6)
            return Six;
        if (i == 7)
            return Seven;
        if (i == 8)
            return Eight;
        if (i == 9)
            return Nine;
        return Empty;
    }

    public String toString() {
        if (this == Empty)
            return " ";
        else
            return String.valueOf(this.ordinal() + 1);
    }
}
