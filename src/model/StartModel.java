package model;

import javax.swing.*;

public class StartModel {
    private final DbConnector db;

    public StartModel(){
        db = new DbConnector();
    }

    public StartModel(String file_path){
        db = new DbConnector(file_path);
    }

    public void setLoadSaveEnable(JButton btn) {
        btn.setEnabled(db.isSaveExist());
    }
}
