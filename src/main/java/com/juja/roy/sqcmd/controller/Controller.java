package com.juja.roy.sqcmd.controller;

import com.juja.roy.sqcmd.commands.Find;
import com.juja.roy.sqcmd.commands.Help;
import com.juja.roy.sqcmd.commands.Tables;
import com.juja.roy.sqcmd.dao.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.juja.roy.sqcmd.view.Writer.toConsole;

public class Controller {

    private static DBConnector dbConnector;
    private static Statement prepareStatement;
    private static ResultSet rs;
    private static String sqlQuery;
    private static Collection<Collection<String>> tableData = new ArrayList<>();

    public static RunState run(String userCommand){
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
                toConsole(Help.getHelpInfo());
                break;
            case "CONNECT":
                  dbConnector = new DBConnector("sqlcmd","root","");
//                dbConnector = new DBConnector(arrUserCommand[1],arrUserCommand[2],arrUserCommand[3]);
                dbConnector.mysqlConnect();
                break;
            case "CREATE":
                toConsole("Команда " + userCommand + " еще не реализована.");
                break;
            case "TABLES":
                toConsole(new Tables(dbConnector).getTables());
                break;
            case "CLEAR":
                toConsole("Команда " + userCommand + " еще не реализована.");
                break;
            case "DROP":
                toConsole("Команда " + userCommand + " еще не реализована.");
                break;
            case "FIND":
                if(arrUserCommand[1] == null || arrUserCommand[1].length() == 0){
                    toConsole("Не введена таблица.");
                }
                toConsole(new Find(dbConnector,arrUserCommand[1]).getTableData());
                break;
            case "INSERT":
                toConsole("Команда " + userCommand + " еще не реализована.");
                break;
            case "UPDATE":
                toConsole("Команда " + userCommand + " еще не реализована.");
                break;
            case "DELETE":
                toConsole("Команда " + userCommand + " еще не реализована.");
                break;

            default:
                toConsole("Неизвестная команда.");
        }

        return RunState.Success;
    }
}
