package com.juja.roy.sqcmd;

import com.juja.roy.sqcmd.controller.Controller;
import com.juja.roy.sqcmd.controller.RunState;
import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import com.juja.roy.sqcmd.exception.DriverLoadException;
import com.juja.roy.sqcmd.view.ConsoleReader;
import com.juja.roy.sqcmd.view.Reader;
import com.juja.roy.sqcmd.view.ConsoleWriter;
import com.juja.roy.sqcmd.view.Writer;

public class Main {
    private static final String WELCOME_MASSAGE = "Приветсвую в SQL-клиенте, написанном по программе обучения на Juja.";
    private static final String REQUEST_COMMAND = "Введите необходимую команду. Для справки введите help. Для выхода введите exit.";
    private static final String BY_MESSAGE = "Приходите еще =).";

    public static void main(String[] args) throws DriverLoadException, ConnectionFailedException, Exception {
        Writer writer = new ConsoleWriter();
        Reader reader = new ConsoleReader();
        Controller controller = new Controller(writer);
        writer.write(WELCOME_MASSAGE);
        while(true) {
            writer.write(REQUEST_COMMAND);
            RunState runState = controller.run(reader.read());

            if(runState.equals(RunState.Exit)){
                break;
            }
        }
        writer.write(BY_MESSAGE);
    }
}
