package com.juja.roy.sqcmd;

import com.juja.roy.sqcmd.controller.Controller;
import com.juja.roy.sqcmd.dao.DBConnector;
import com.juja.roy.sqcmd.view.Reader;

import static com.juja.roy.sqcmd.view.Writer.toConsole;

public class Main {
    private static String welcomeMassage = "Приветсвую в SQL-клиенте, написанном по программе обучения на Juja.";
    private static String requestCommand = "Введите необходимую команду. Для справки введите help. Для выхода введите exit.";
    private static String byMessage = "Приходите еще =).";

    public static void main(String[] args) {
        toConsole(welcomeMassage);
        while(true) {
            toConsole(requestCommand);
            int stateController = Controller.run(Reader.readConsole());

            if(stateController <= 0){
                toConsole(byMessage);
                System.exit(stateController);
            }
        }
    }
}
