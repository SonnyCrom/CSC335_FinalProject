package view;

import javax.swing.*;

public class HintBtn extends JButton implements BtnObserver {
    @Override
    public void setDisable() {
        this.setEnabled(false);
    }

    @Override
    public void setText(int num) {
        this.setText("Hints: " + num);
    }
}
