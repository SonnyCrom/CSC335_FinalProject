package model;

import javax.swing.*;

public class StartModel {
    private final DbConnector db;

    public StartModel(){
        db = new DbConnector();
    }

    public void setLoadSaveEnable(JButton btn) {
        btn.setEnabled(db.isSaveExist());
    }
}
