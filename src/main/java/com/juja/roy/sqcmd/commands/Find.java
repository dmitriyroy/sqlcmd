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
        sqlQueryColumns = "SHOW COLUMNS FROM " + table + " FROM " + dbConnector.getDatabase();
        sqlQueryTableData = "SELECT * FROM " + table;
        tableData = new ArrayList<>();
    }

    public List<String> getColumns(){
        List<String> tableColumns = new ArrayList<>();
        try {
            rs = dbConnector.getConnection().createStatement().executeQuery(sqlQueryColumns);
            while(rs.next()) {
                tableColumns.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableColumns;
    }

    public Collection<Collection<String>> getTableData() {
        this.columns = getColumns();
        tableData.add(this.columns);

        try {
            rs = dbConnector.getConnection().createStatement().executeQuery(sqlQueryTableData);
            List<String> row = new ArrayList<>();
            while(rs.next()) {
                for(int i=0; i<this.columns.size(); i++) {
                    row.add(rs.getString(this.columns.get(i)));
                }
            }
            tableData.add(row);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableData;
    }

}
