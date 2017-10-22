package com.juja.roy.sqcmd.controller;

import com.juja.roy.sqcmd.commands.Find;
import com.juja.roy.sqcmd.commands.Help;
import com.juja.roy.sqcmd.commands.Tables;
import com.juja.roy.sqcmd.dao.DBConnector;
import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import com.juja.roy.sqcmd.exception.DriverLoadException;
import com.juja.roy.sqcmd.view.ConsoleReader;
import com.juja.roy.sqcmd.view.ConsoleWriter;
import com.juja.roy.sqcmd.view.Reader;
import com.juja.roy.sqcmd.view.Writer;

import java.sql.SQLException;
import java.util.Arrays;

import static java.lang.String.format;

public class Controller {
    private final String WELCOME_MASSAGE = "Приветствую в SQL-клиенте, написанном по программе обучения на Juja.";
    private final String REQUEST_COMMAND = "Введите необходимую команду. Для справки введите help. Для выхода введите exit.";
    private final String BY_MESSAGE = "Приходите еще =).";
    private final String ERROR_CONNECT_DATABASE = "Ошибка коннекта к базе данных %s по причине \"%s\".";
    private final String DATABASE_CONNECTION_SUCCESS = "Database connection SUCCESS.";
    private final String ERROR_RUN_COMMAND = "Ошибка обращения к базе данных коммандой %s по причине \"%s\".";
    private final String TABLE_NOT_INPUT = "Не введена таблица.";
    private final String UNKNOWN_COMMAND = "Неизвестная команда.";
    private final String NEED_CONNECT = "Вам необходимо подключиться к базе. Формат команды: connect|database|username|password";

    private DBConnector dbConnector;
    private final Writer writer = new ConsoleWriter();
    private final Reader reader = new ConsoleReader();

    public void run() {
        RunState runState = null;
        writer.write(WELCOME_MASSAGE);
        int extraExit = 0;
        while (true) {
            do {
                writer.write(REQUEST_COMMAND);
                runState = iterate(reader.read());
            } while (runState.toString().equals("EmptyCommand"));

            if (runState != null) {
                if (runState.equals(RunState.Exit)) {
                    break;
                }
            }
            if (extraExit++ > 5) {
                break;
            }
        }
        writer.write(BY_MESSAGE);
    }

    public RunState iterate(String userCommand) {
        if (userCommand == null || userCommand.trim().equals("")) {
            return RunState.EmptyCommand;
        }
        if (userCommand.equalsIgnoreCase("exit")) {
            return RunState.Exit;
        }

        String[] tmpArrUserCommand = userCommand.replace(" ", "").split("\\|");
        String[] commandParams = Arrays.copyOfRange(tmpArrUserCommand, 1, tmpArrUserCommand.length);
        String command = tmpArrUserCommand[0].toUpperCase();

        //todo - проверять наличие коннекта к базе
        switch (command) {
            case "HELP":
                writer.write(new Help().getHelpInfo());
                break;
            case "CONNECT":
                try {
//                    dbConnector = new DBConnector("sqlcmd","root","");
//                    dbConnector = new DBConnector("test_database","root","");
                    dbConnector = new DBConnector(commandParams[0], commandParams[1], commandParams[2]);
                    dbConnector.mysqlConnect();
                    writer.write(DATABASE_CONNECTION_SUCCESS);
                } catch (Exception | ConnectionFailedException e) {
                    writer.write(String.format(ERROR_CONNECT_DATABASE, commandParams[0], e.getMessage()));
                } catch (DriverLoadException e) {
                    writer.write(String.format(ERROR_CONNECT_DATABASE, commandParams[0], e.getMessage()));
                }
                break;
            case "CREATE":
                writer.write("Команда " + userCommand + " еще не реализована.");
                break;
            case "TABLES":
                if (dbConnector != null) {
                    try {
                        writer.write(new Tables(dbConnector).getTables());
                    } catch (SQLException e) {
                        writer.write(format(ERROR_RUN_COMMAND, command, e.getMessage()));
                    }
                } else {
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
                if (dbConnector != null) {
                    if (commandParams.length == 0) {
                        writer.write(TABLE_NOT_INPUT);
                        break;
                    }
                    try {
                        writer.write(new Find(dbConnector, commandParams[0]).getTableData());
                    } catch (SQLException e) {
                        writer.write(format(ERROR_RUN_COMMAND, command, e.getMessage()));
                    }
                } else {
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
