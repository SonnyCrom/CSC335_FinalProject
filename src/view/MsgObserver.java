package view;

public interface MsgObserver {
    void newNumber(int num);

    void hint(boolean haveHints);

    void incorrect(int num);
}
