package com.juja.roy.sqcmd.view;

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

    @Before
    public void setup(){
        scanner = new Scanner(System.in);

        consoleReader = new ConsoleReader();
    }

    @Ignore
    @Test
    public void readTest(){
//        assertEquals("DDD", consoleReader.read());
//        assertEquals(null, consoleReader.read());
    }
}
