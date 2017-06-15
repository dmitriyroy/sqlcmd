package com.juja.roy.sqcmd.dao;

import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import com.juja.roy.sqcmd.exception.DriverLoadException;
import com.juja.roy.sqcmd.view.ConsoleWriter;
import com.juja.roy.sqcmd.view.Writer;

import java.sql.*;
public class DBConnector {

    private Connection connection;
    // | database | username | password
    private String database;
    private String username;
    private String password;
    private Writer writer;

    public DBConnector(String database, String username, String password){
        writer = new ConsoleWriter();
        this.database = database;
        this.username = username;
        this.password = password;
        if(database == null){
            try {
                throw new Exception("Невалидное имя базы данных");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if(username == null){
            try {
                throw new Exception("Невалидное имя пользователя");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if(password == null){
            this.password = "";
//            try {
//                throw new Exception("Невалидный пароль");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    public void mysqlConnect() throws DriverLoadException, ConnectionFailedException {
        ////////////////////////////////////////////////////////////////////////////////////
//        write("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DriverLoadException("Where is your MySQL JDBC Driver?");
        }

        try {
            connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/sqlcmd","root", "");
                    .getConnection("jdbc:mysql://localhost:3306/" + this.database, this.username, this.password);

        } catch (SQLException e) {
            throw new ConnectionFailedException("Connection Failed! Check output console.");
        }

        if (connection == null) {
            throw new ConnectionFailedException("Failed to make connection!");
        }
        /////////////////////////////////////////////////////////////
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
