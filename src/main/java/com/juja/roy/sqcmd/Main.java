package com.juja.roy.sqcmd;

import com.juja.roy.sqcmd.controller.Controller;
import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import com.juja.roy.sqcmd.exception.DriverLoadException;

public class Main {
    public static void main(String[] args) throws DriverLoadException, ConnectionFailedException {
        new Controller().run();
    }
}
