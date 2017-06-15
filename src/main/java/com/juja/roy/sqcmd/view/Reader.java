package com.juja.roy.sqcmd.view;

import java.util.Scanner;

public class Reader implements ReaderInterface {
    @Override
    public String readConsole(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
