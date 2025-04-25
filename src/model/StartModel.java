package model;

import javax.swing.*;

public class StartModel {
    private DbConnector db;

    public StartModel(){
        db = new DbConnector();
    }

    public void setLoadSaveEnable(JButton btn) {
        btn.setEnabled(db.isSaveExist());
    }
}
