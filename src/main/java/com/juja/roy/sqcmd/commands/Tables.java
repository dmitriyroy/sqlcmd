package com.juja.roy.sqcmd.commands;

import com.juja.roy.sqcmd.dao.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tables {
    private DBConnector dbConnector;
    private String sqlQuery;
    private ResultSet rs;
    private List<String> tableList;

    public Tables(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
        sqlQuery = "SHOW TABLES FROM " + dbConnector.getDatabase();
        tableList = new ArrayList<>();
    }

    public List<String> getTables() {
        try {
            rs = dbConnector.getConnection().createStatement().executeQuery(sqlQuery);
            tableList.add("tables");
            while (rs.next()) {
                tableList.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

}
