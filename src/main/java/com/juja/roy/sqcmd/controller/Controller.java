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
import java.util.Arrays;
import java.util.Collection;

import static java.lang.String.format;

public class Controller {

    private static final String ERROR_CONNECT_DATABASE = "Ошибка коннекта к базе данных %s по причине \"%s\".";
    private static final String DATABASE_CONNECTION_SUCCESS = "Database connection SUCCESS.";
    private static final String ERROR_RUN_COMMAND = "Ошибка обращения к базе данных коммандой %s по причине \"%s\".";
    private static final String TABLE_NOT_INPUT = "Не введена таблица.";
    private static final String UNKNOWN_COMMAND = "Неизвестная команда.";
    private static final String NEED_CONNECT = "Вам необходимо подключиться к базе. Формат команды: connect | database | username | password";
    private static DBConnector dbConnector;

    private static Collection<Collection<String>> tableData = new ArrayList<>();
    private final Writer writer;

    public Controller(Writer writer) {
        this.writer = writer;
    }

    public RunState run(String userCommand) throws DriverLoadException, ConnectionFailedException {
        if(userCommand == null || userCommand.trim().equals("")){
            return RunState.EmptyCommand;
        }
        if(userCommand.equalsIgnoreCase("exit")) {
            return RunState.Exit;
        }

        String[] tmpArrUserCommand = userCommand.replace(" ","").split("\\|");
        String[] commandParams = Arrays.copyOfRange(tmpArrUserCommand, 1, tmpArrUserCommand.length);
        String command = tmpArrUserCommand[0].toUpperCase();

        //todo - проверять наличие коннекта к базе
        switch(command){
            case "HELP":
                writer.write(new Help().getHelpInfo());
                break;
            case "CONNECT":
                try {
//                dbConnector = new DBConnector("sqlcmd","root","");
//                    dbConnector = new DBConnector("test_database","root","");
                    dbConnector = new DBConnector(commandParams[0],commandParams[1],commandParams[2]);
                    dbConnector.mysqlConnect();
                    writer.write(DATABASE_CONNECTION_SUCCESS);
                } catch (Exception e) {
                    writer.write(String.format(ERROR_CONNECT_DATABASE,commandParams[0],e.getMessage()));
                }
                break;
            case "CREATE":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "TABLES":
                if(dbConnector != null){
                    try {
                        writer.write(new Tables(dbConnector).getTables());
                    } catch (SQLException e) {
                        writer.write(format(ERROR_RUN_COMMAND,command,e.getMessage()));
                    }
                }else{
                    writer.write(NEED_CONNECT);
                }
                break;
            case "CLEAR":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "DROP":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "FIND":
                if(dbConnector != null) {
                    if (commandParams.length == 0) {
                        writer.write(TABLE_NOT_INPUT);
                        break;
                    }
                    try {
                        writer.write(new Find(dbConnector, commandParams[0]).getTableData());
                    } catch (SQLException e) {
                        writer.write(format(ERROR_RUN_COMMAND, command, e.getMessage()));
                    }
                }else{
                    writer.write(NEED_CONNECT);
                }
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
                writer.write(UNKNOWN_COMMAND);
        }

        return RunState.Success;
    }
}
