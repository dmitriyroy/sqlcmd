package com.juja.roy.sqcmd.view;

import java.util.Scanner;

public class ConsoleReader implements Reader {
    @Override
    public String read(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
