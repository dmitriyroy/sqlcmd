package com.juja.roy.sqcmd.commands;

import com.juja.roy.sqcmd.dao.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Find {
    private DBConnector dbConnector;
    private String sqlQueryColumns;
    private String sqlQueryTableData;
    private ResultSet rs;
    private String table;
    private List<String> columns;
    private Collection<Collection<String>> tableData;

    public Find(DBConnector dbConnector, String table) {
        this.dbConnector = dbConnector;
        this.table = table;
        // MySQL
//        sqlQueryColumns = "SHOW COLUMNS FROM " + table + " FROM " + dbConnector.getDatabase();
        // PostgreSQL
        sqlQueryColumns = "SELECT column_name FROM information_schema.columns WHERE table_name ='" + table + "' ";
        sqlQueryTableData = "SELECT * FROM " + table;
        tableData = new ArrayList<>();
    }

    private List<String> getColumns() throws SQLException {
        List<String> tableColumns = new ArrayList<>();
        rs = dbConnector.getConnection().createStatement().executeQuery(sqlQueryColumns);
        while(rs.next()) {
            tableColumns.add(rs.getString(1));
        }
        return tableColumns;
    }

    public Collection<Collection<String>> getTableData() throws SQLException {
        this.columns = getColumns();
        tableData.add(this.columns);

        rs = dbConnector.getConnection().createStatement().executeQuery(sqlQueryTableData);
        while(rs.next()) {
            List<String> row = new ArrayList<>();
            for (String column : this.columns) {
                row.add(rs.getString(column));
            }
            tableData.add(row);
        }

        return tableData;
    }
}
