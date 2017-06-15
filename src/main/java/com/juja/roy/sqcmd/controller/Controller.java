package com.juja.roy.sqcmd.controller;

import com.juja.roy.sqcmd.commands.Find;
import com.juja.roy.sqcmd.commands.Help;
import com.juja.roy.sqcmd.commands.Tables;
import com.juja.roy.sqcmd.dao.DBConnector;
import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import com.juja.roy.sqcmd.exception.DriverLoadException;
import com.juja.roy.sqcmd.view.Writer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Controller {

    private static DBConnector dbConnector;
    private static Statement prepareStatement;
    private static ResultSet rs;
    private static String sqlQuery;
    private static Collection<Collection<String>> tableData = new ArrayList<>();
    private final Writer writer;

    public Controller(Writer writer) {
        this.writer = writer;
    }


    public RunState run(String userCommand) throws DriverLoadException, ConnectionFailedException, Exception {
        if(userCommand == null){
            return RunState.EmptyCommand;
        }
        if(userCommand.equalsIgnoreCase("exit")) {
            return RunState.Exit;
        }

        String[] tmpArrUserCommand = userCommand.split("\\|");
        String[] arrUserCommand = new String[4];
        for(int i = 0; i < 4; i++){
            if(tmpArrUserCommand.length < i+1){
                arrUserCommand[i] = null;
            }else{
                arrUserCommand[i] = tmpArrUserCommand[i];
            }
        }

        switch(arrUserCommand[0].toUpperCase()){
            case "HELP":
                writer.write(Help.getHelpInfo());
                break;
            case "CONNECT":
                  dbConnector = new DBConnector("sqlcmd","root","");
//                dbConnector = new DBConnector(arrUserCommand[1],arrUserCommand[2],arrUserCommand[3]);
                dbConnector.mysqlConnect();
                writer.write("Database connection SUCCESS.");
                break;
            case "CREATE":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "TABLES":
                writer.write(new Tables(dbConnector).getTables());
                break;
            case "CLEAR":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "DROP":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "FIND":
                if(arrUserCommand[1] == null || arrUserCommand[1].length() == 0){
                    writer.write("Не введена таблица.");
                }
                writer.write(new Find(dbConnector,arrUserCommand[1]).getTableData());
                break;
            case "INSERT":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "UPDATE":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "DELETE":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;

            default:
                writer.write("Неизвестная команда.");
        }

        return RunState.Success;
    }
}
