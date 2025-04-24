package model;

import DB.DbJson;
import DB.IDb;

public class DbConnector {
    private final IDb db;

    public DbConnector() {
        this.db = new DbJson();
    }
}
