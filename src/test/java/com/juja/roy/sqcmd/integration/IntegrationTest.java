package com.juja.roy.sqcmd.integration;

import com.juja.roy.sqcmd.Main;
import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import com.juja.roy.sqcmd.exception.DriverLoadException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static ConfigurableInputStream in;
    private static ByteArrayOutputStream out;

    @Before
    public void setup(){
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testExit() throws DriverLoadException, Exception, ConnectionFailedException {
        // given
//        in.add(" ");
        in.add("help");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("", getData());
    }

    private String getData() {
        try {
            return new String(out.toByteArray(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}
