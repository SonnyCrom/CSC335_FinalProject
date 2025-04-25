package view;

import model.Numbers;

import javax.swing.*;
import java.awt.*;

public class NumberBtn extends JButton implements NumberBtnObserver {
    public NumberBtn(Color color) {
        super(" ");
        this.setBackground(color);
        this.setForeground(new Color(0,0,0));
        this.setFont(new Font("Arial", Font.PLAIN, 32));
    }

    public void setDisable() {
        this.setEnabled(false);
    }

    public void setText(Numbers num) {
        this.setText(num.toString());
    }
}
