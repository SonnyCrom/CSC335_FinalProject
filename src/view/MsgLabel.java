package view;

import javax.swing.*;

public class MsgLabel extends JLabel implements MsgObserver {
    public MsgLabel() {
        super("Select number", JLabel.LEFT);
        this.setSize(250, 100);
    }

    public void newNumber(int num) {
        this.setText("Current number: " + Integer.toString(num));
    }

    public void hint(boolean haveHints) {
        if (haveHints) {
            this.setText("Select cell for hint");
        } else {
            this.setText("No more hints available");
        }
    }

    public void incorrect(int num) {
        this.setText("Incorrect!\nCurrent number: " + Integer.toString(num));
    }

}
