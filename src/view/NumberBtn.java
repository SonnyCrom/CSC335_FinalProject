package view;

import model.Numbers;

import javax.swing.*;
import java.awt.*;

public class NumberBtn extends JButton implements NumberBtnObserver {
    public NumberBtn(Color color) {
        super(" ");
        this.setBackground(color);
    }

    public void setDisable() {
        this.setEnabled(false);
    }

    public void setText(Numbers num) {
        this.setText(num.toString());
    }
}
