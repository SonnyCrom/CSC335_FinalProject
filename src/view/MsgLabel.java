package view;

import javax.swing.*;

public class MsgLabel extends JLabel implements MsgObserver {
    public MsgLabel() {
        super("Select number", JLabel.LEFT);
        this.setSize(250, 100);
    }

    public void newNumber(int num) {
        if (num == 0) {
            this.setText("Current number: empty");
        } else {
            this.setText("Current number: " + num);
        }
    }

    public void hint(boolean haveHints) {
        if (haveHints) {
            this.setText("Select cell for hint");
        } else {
            this.setText("No more hints available");
        }
    }

    public void incorrect(int num) {
        if (num == 0) {
            this.setText("Incorrect! Current number: empty");
        } else {
            this.setText("Incorrect! Current number: " + num);
        }
    }

}
