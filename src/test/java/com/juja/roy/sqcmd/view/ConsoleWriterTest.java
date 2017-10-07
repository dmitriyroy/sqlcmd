package com.juja.roy.sqcmd.view;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class ConsoleWriterTest {

    private ConsoleWriter consoleWriter;

    @Before
    public void runT(){
        consoleWriter = new ConsoleWriter();
    }

    @Test
    public void writeString(){
        String assertString = "Good line.";
        assertEquals(assertString, consoleWriter.write("Good line."));
    }

    @Test
    public void writeStringNull(){
        String s = null;
        assertEquals(null, consoleWriter.write(s));
    }

    @Test
    public void writeListNull(){
        String assertString = "Empty list.";
        List<String> list = null;
        assertEquals(assertString, consoleWriter.write(list));
    }

    @Test
    public void writeListEmpty(){
        String assertString = "Empty list.";
        List<String> list = new ArrayList<>();
        assertEquals(assertString, consoleWriter.write(list));
    }

    @Test
    public void writeList(){
        List<String> list = new ArrayList<>();
        list.add("tables");
        list.add("user");
        String assertText = "+--------+\n" +
                            "+ tables +\n" +
                            "+--------+\n" +
                            "+ user   +\n" +
                            "+--------+";
        assertEquals(assertText, consoleWriter.write(list));
    }

    @Test
    public void writeCollectionNull(){
        String assertString = "Empty tableData.";
        Collection<Collection<String>>  collection = null;
        assertEquals(assertString, consoleWriter.write(collection));
    }

    @Test
    public void writeCollectionEmpty(){
        String assertString = "Empty tableData.";
        Collection<Collection<String>>  collection = new ArrayList<>();
        assertEquals(assertString, consoleWriter.write(collection));
    }

    @Test
    public void writeCollection(){
        String assertString = "+-------+-----+--------+\n" +
                "+ name  + aqe + sex    +\n" +
                "+-------+-----+--------+\n" +
                "+ Vasya + 28  + mail   +\n" +
                "+ Masha + 15  + femail +\n" +
                "+-------+-----+--------+\n";
        Collection<Collection<String>>  collection = new ArrayList<>();
        List<String> row1 = new ArrayList<>();
        row1.add("name");
        row1.add("aqe");
        row1.add("sex");
        List<String> row2 = new ArrayList<>();
        row2.add("Vasya");
        row2.add("28");
        row2.add("mail");
        List<String> row3 = new ArrayList<>();
        row3.add("Masha");
        row3.add("15");
        row3.add("femail");
        collection.add(row1);
        collection.add(row2);
        collection.add(row3);
        assertEquals(assertString, consoleWriter.write(collection));
    }



}