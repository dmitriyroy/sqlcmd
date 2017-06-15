package com.juja.roy.sqcmd.dao;

import java.sql.*;

import static com.juja.roy.sqcmd.view.Writer.toConsole;

public class DBConnector {

    private Connection connection;
//    private static Statement prepareStatement;
//    private static ResultSet rs;
    // | database | username | password
    private String database;
    private String username;
    private String password;

    public DBConnector(String database, String username, String password){
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

    public void mysqlConnect(){
        ////////////////////////////////////////////////////////////////////////////////////
//        toConsole("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            toConsole("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

//        toConsole("MySQL JDBC Driver Registered!");

        try {
            connection = DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/sqlcmd","root", "");
                    .getConnection("jdbc:mysql://localhost:3306/" + this.database, this.username, this.password);
            toConsole("Database connection SUCCESS.");

        } catch (SQLException e) {
            toConsole("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
//            toConsole("You made it, take control your database now!");
        } else {
            toConsole("Failed to make connection!");
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
