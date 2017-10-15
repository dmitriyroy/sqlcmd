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
    private static final String WELCOME_MASSAGE = "Приветствую в SQL-клиенте, написанном по программе обучения на Juja.";
    private static final String REQUEST_COMMAND = "Введите необходимую команду. Для справки введите help. Для выхода введите exit.";
    private static final String NEED_CONNECT = "Вам необходимо подключиться к базе. Формат команды: connect | database | username | password";
    private static final String BY_MESSAGE = "Приходите еще =).";

    public static void main(String[] args) throws DriverLoadException, ConnectionFailedException {
        Writer writer = new ConsoleWriter();
        Reader reader = new ConsoleReader();
        Controller controller = new Controller(writer);
        writer.write(WELCOME_MASSAGE);
        RunState runState = null;
        int extraeXIT = 0;
        while(true) {
            try {
                do {
                    writer.write(REQUEST_COMMAND);
                    runState = controller.run(reader.read());
                } while (runState.toString().equals("EmptyCommand"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(runState != null){
                if(runState.equals(RunState.Exit)) {
                    break;
                }
//                writer.write(NEED_CONNECT);
            }
            if(extraeXIT++ > 5){
                break;
            }
        }
        writer.write(BY_MESSAGE);
    }
}
