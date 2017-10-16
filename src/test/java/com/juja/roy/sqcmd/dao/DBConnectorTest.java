package com.juja.roy.sqcmd.dao;

import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import com.juja.roy.sqcmd.view.ConsoleWriter;
import com.juja.roy.sqcmd.view.Writer;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DBConnectorTest {
    Writer writer = new ConsoleWriter();
    public Connection connection;

    //todo - переписать, когда буду знать, как правильно написать тест DBConnector-ф
    @Before
    public void setup() throws ConnectionFailedException {
        try {
            connection = DriverManager
//                .getConnection("jdbc:mysql://localhost:3306/sqlcmd", "root", "");
                .getConnection("jdbc:postgresql://localhost:5432/test_database", "postgres", "postgres");
        }catch(SQLException e){
            throw new ConnectionFailedException("Connection Failed! Check output console.");
        }
    }

    @Test
    public void getConnectionTest(){
        //todo - переписать, когда буду знать, как правильно написать тест DBConnector
        try {
            DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/sqlcmd", "root", "").getCatalog();
                    .getConnection("jdbc:postgresql://localhost:5432/test_database", "postgres", "postgres").getCatalog();
            assertEquals(connection.getCatalog(), DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/sqlcmd", "root", "").getCatalog());
                    .getConnection("jdbc:postgresql://localhost:5432/test_database", "postgres", "postgres").getCatalog());
        } catch (SQLException e) {
            writer.write(String.format("Ошибка подключения к базе sqlcmd по причине \"%s\"",e.getMessage()));
            assertTrue(false);
        }
    }

    @Test
    public void getConnectionTestBadUser(){
        //todo - переписать, когда буду знать, как правильно написать тест DBConnector
//        InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "windows-1251");
//        BufferedReader br = new BufferedReader(isr);
        try {
            DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/sqlcmd", "postgres", "postgres");
                    .getConnection("jdbc:postgresql://localhost:5432/test_database", "user", "postgres");
        } catch (SQLException e) {
//            "пользователь "postgres1" не прошёл проверку подлинности (по паролю)"
            assertEquals("Ошибка подключения к базе sqlcmd по причине " +
                    "\"ВАЖНО: пользователь \"user\" не прошёл проверку подлинности (по паролю)\".",
                    String.format("Ошибка подключения к базе test_database по причине \"%s\".", e.getMessage()));
        }
    }
}
