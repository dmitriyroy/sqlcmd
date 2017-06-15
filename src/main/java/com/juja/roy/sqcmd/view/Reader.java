package com.juja.roy.sqcmd.view;

import java.util.Scanner;

public class Reader {
    public static String readConsole(){
        String outString = null;
        Scanner scaner = new Scanner(System.in);
        outString = scaner.nextLine();
        return outString;
    }
}
