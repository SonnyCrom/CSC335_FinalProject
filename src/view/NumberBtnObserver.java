package view;

import model.Numbers;

public interface NumberBtnObserver {
    void setDisable();

    void setText(Numbers num);
}
