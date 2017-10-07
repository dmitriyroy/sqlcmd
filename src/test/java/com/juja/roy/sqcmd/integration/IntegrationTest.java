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
        in.add("help");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Приветствую в SQL-клиенте, написанном по программе обучения на Juja.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Список доступных комманд:\r\n" +
                "сonnect    \r\n" +
                "\tКоманда для подключения к соответствующей БД\r\n" +
                "\t\tФормат команды: connect | database | username | password\r\n" +
                "\t\t\tгде: database - имя БД\r\n" +
                "\t\t\t     username - имя пользователя БД\r\n" +
                "\t\t\t     password - пароль пользователя БД\r\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\r\n" +
                "tables\r\n" +
                "\tКоманда выводит список всех таблиц \r\n" +
                "\t\tФормат: tables (без параметров)\r\n" +
                "\t\tФормат вывода: \r\n" +
                "\t\t\tв любом удобном формате\r\n" +
                "\t\t\t\tнапример [table1, table2, table3]\r\n" +
                "clear\r\n" +
                "\tКоманда очищает содержимое указанной (всей) таблицы\r\n" +
                "\t\tФормат: clear | tableName\r\n" +
                "\t\t\tгде tableName - имя очищаемой таблицы\r\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\r\n" +
                "drop\r\n" +
                "\tКоманда удаляет заданную таблицу\r\n" +
                "\t\tФормат: drop | tableName\r\n" +
                "\t\t\tгде tableName - имя удаляемой таблицы\r\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\r\n" +
                "create\r\n" +
                "\tКоманда создает новую таблицу с заданными полями\r\n" +
                "\t\tФормат: create | tableName | column1 | column2 | ... | columnN \r\n" +
                "\t\t\tгде: tableName - имя таблицы\r\n" +
                "\t\t\t     column1 - имя первого столбца записи \r\n" +
                "\t\t\t     column2 - имя второго столбца записи \r\n" +
                "\t\t\t     columnN - имя n-го столбца записи \r\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\r\n" +
                "find \r\n" +
                "\tКоманда для получения содержимого указанной таблицы\r\n" +
                "\t\tФормат: find | tableName\r\n" +
                "\t\t\tгде tableName - имя таблицы\r\n" +
                "\t\tФормат вывода: табличка в консольном формате\r\n" +
                "\t\t\t\t       +--------+---------+------------------+\r\n" +
                "\t\t\t\t       +  col1  +  col2   +       col3       +\r\n" +
                "\t\t\t\t       +--------+---------+------------------+\r\n" +
                "\t\t\t\t       +  123   +  stiven +     pupkin       +\r\n" +
                "\t\t\t\t       +  345   +  eva    +     pupkina      +\r\n" +
                "\t\t\t\t       +--------+---------+------------------+\r\n" +
                "insert\r\n" +
                "\tКоманда для вставки одной строки в заданную таблицу\r\n" +
                "\t\tФормат: insert | tableName | column1 | value1 | column2 | value2 | ... | columnN | valueN\r\n" +
                "\t\t\tгде: tableName - имя таблицы\r\n" +
                "\t\t\t     column1   - имя первого столбца записи \r\n" +
                "\t\t\t     value1    - значение первого столбца записи \r\n" +
                "\t\t\t     column2   - имя второго столбца записи \r\n" +
                "\t\t\t     value2    - значение второго столбца записи \r\n" +
                "\t\t\t     columnN   - имя n-го столбца записи \r\n" +
                "\t\t\t     valueN    - значение n-го столбца записи \r\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\r\n" +
                "update\r\n" +
                "\tКоманда обновит запись, установив значение column2 = value2, для которой соблюдается условие column1 = value1 \r\n" +
                "\t\tФормат: update | tableName | column1 | value1 | column2 | value2\r\n" +
                "\t\t\tгде: tableName - имя таблицы\r\n" +
                "\t\t\t     column1 - имя столбца записи которое проверяется\r\n" +
                "\t\t\t     value1  - значение которому должен соответствовать столбец column1 для обновляемой записи\r\n" +
                "\t\t\t     column2 - имя обновляемого столбца записи \r\n" +
                "\t\t\t     value2  - значение обновляемого столбца записи \r\n" +
                "\t\t\t     columnN - имя n-го обновляемого столбца записи \r\n" +
                "\t\t\t     valueN  - значение n-го обновляемого столбца записи \r\n" +
                "\t\tФормат вывода: табличный, как при find со старыми значениями обновленных записей.\r\n" +
                "delete\r\n" +
                "\tКоманда удаляет одну или несколько записей для которых соблюдается условие column = value\r\n" +
                "\t\tФормат: delete | tableName | column | value\r\n" +
                "\t\t\tгде: tableName - имя таблицы\r\n" +
                "\t\t\t     column - имя столбца записи которое проверяется\r\n" +
                "\t\t\t     value  - значение которому должен соответствовать столбец column1 для удаляемой записи\r\n" +
                "\t\tФормат вывода: табличный, как при find со старыми значениями удаляемых записей.\r\n" +
                "help \r\n" +
                "\tКоманда выводит в консоль список всех доступных команд\r\n" +
                "\t\tФормат: help (без параметров)\r\n" +
                "\t\tФормат вывода: текст, описания команд с любым форматированием\r\n" +
                "exit \r\n" +
                "\tКоманда для отключения от БД и выход из приложения\r\n" +
                "\t\tФормат: exit (без параметров)\r\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\r\n" +
                "\r\n" +
                "Приходите еще =).\r\n", getData());
    }

    private String getData() {
        try {
            return new String(out.toByteArray(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}