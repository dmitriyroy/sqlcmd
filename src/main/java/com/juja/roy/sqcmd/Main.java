package com.juja.roy.sqcmd;

import com.juja.roy.sqcmd.controller.Controller;
import com.juja.roy.sqcmd.view.Reader;

import static com.juja.roy.sqcmd.view.Writer.toConsole;

public class Main {
    private static final String WELCOME_MASSAGE = "Приветсвую в SQL-клиенте, написанном по программе обучения на Juja.";
    private static final String REQUEST_COMMAND = "Введите необходимую команду. Для справки введите help. Для выхода введите exit.";
    private static final String BY_MESSAGE = "Приходите еще =).";

    public static void main(String[] args) {
        toConsole(WELCOME_MASSAGE);
        while(true) {
            toConsole(REQUEST_COMMAND);
            int stateController = Controller.run(Reader.readConsole());

            if(stateController <= 0){
                toConsole(BY_MESSAGE);
                System.exit(stateController);
            }
        }
    }
}
