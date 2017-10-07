package com.juja.roy.sqcmd.dao;

import com.juja.roy.sqcmd.exception.ConnectionFailedException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DBConnectorTest {
    public Connection connection;
    //todo - переписать, когда буду знать, как правильно написать тест DBConnector-ф
    @Before
    public void setup() throws ConnectionFailedException {
        try {
            connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/sqlcmd", "root", "");
        }catch(SQLException e){
            throw new ConnectionFailedException("Connection Failed! Check output console.");
        }
    }

    @Test
    public void getConnectionTest(){
        //todo - переписать, когда буду знать, как правильно написать тест DBConnector-ф
        try {
            assertEquals(connection.getCatalog(), DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sqlcmd", "root", "").getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            connection.assert(DriverManager
//                    .getConnection("jdbc:mysql://localhost:3306/sqlcmd", "root", ""));
//        }catch(SQLException e){
//
//        }
    }
}
