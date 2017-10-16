package com.juja.roy.sqcmd.view;

import com.juja.roy.sqcmd.integration.ConfigurableInputStream;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class ConsoleReaderTest {

    //todo - не знаю как замокать сканер с консоли
    Scanner scanner = null;
    ConsoleReader consoleReader = null;
    private static ConfigurableInputStream in;

    @Before
    public void setup(){
        scanner = new Scanner(System.in);
        consoleReader = new ConsoleReader();
        in = new ConfigurableInputStream();
        System.setIn(in);
    }

    @Test
    public void testRead(){
        in.add("connect");
        assertEquals("connect", consoleReader.read());
    }
}
