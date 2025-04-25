package view;

import model.Numbers;

import javax.swing.*;
import java.awt.*;

public class NumberBtn extends JButton implements BtnObserver {
    public NumberBtn(Color color) {
        super(" ");
        this.setBackground(color);
        this.setForeground(new Color(0, 0, 0));
        this.setFont(new Font("Arial", Font.PLAIN, 32));
    }

    @Override
    public void setDisable() {
        this.setEnabled(false);
    }

    @Override
    public void setText(int num) {
        Numbers enumNum = Numbers.fromInteger(num);
        super.setText(enumNum.toString());
    }
}
