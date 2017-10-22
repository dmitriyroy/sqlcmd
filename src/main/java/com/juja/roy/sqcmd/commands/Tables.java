package com.juja.roy.sqcmd.commands;

import com.juja.roy.sqcmd.dao.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tables {
    private DBConnector dbConnector;
    private String sqlQuery;
    private ResultSet rs;

    public Tables(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
//        sqlQuery = "SHOW TABLES FROM " + dbConnector.getDatabase();
        sqlQuery = "SELECT table_name                " +
                "  FROM information_schema.tables " +
                " WHERE table_type = 'BASE TABLE' " +
                "   AND table_schema NOT IN ('pg_catalog', 'information_schema')";
    }

    public List<String> getTables() throws SQLException {
        List<String> tableList = new ArrayList<>();

        rs = dbConnector.getConnection().createStatement().executeQuery(sqlQuery);
        tableList.add("tables");
        while (rs.next()) {
            tableList.add(rs.getString(1));
        }

        return tableList;
    }

}
