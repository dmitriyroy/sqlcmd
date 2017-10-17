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
    public void testHelp() throws DriverLoadException, Exception, ConnectionFailedException {
        // given
        in.add("help");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Приветствую в SQL-клиенте, написанном по программе обучения на Juja.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Список доступных комманд:\n" +
                "сonnect    \n" +
                "\tКоманда для подключения к соответствующей БД\n" +
                "\t\tФормат команды: connect | database | username | password\n" +
                "\t\t\tгде: database - имя БД\n" +
                "\t\t\t     username - имя пользователя БД\n" +
                "\t\t\t     password - пароль пользователя БД\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\n" +
                "tables\n" +
                "\tКоманда выводит список всех таблиц \n" +
                "\t\tФормат: tables (без параметров)\n" +
                "\t\tФормат вывода: \n" +
                "\t\t\tв любом удобном формате\n" +
                "\t\t\t\tнапример [table1, table2, table3]\n" +
                "clear\n" +
                "\tКоманда очищает содержимое указанной (всей) таблицы\n" +
                "\t\tФормат: clear | tableName\n" +
                "\t\t\tгде tableName - имя очищаемой таблицы\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\n" +
                "drop\n" +
                "\tКоманда удаляет заданную таблицу\n" +
                "\t\tФормат: drop | tableName\n" +
                "\t\t\tгде tableName - имя удаляемой таблицы\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\n" +
                "create\n" +
                "\tКоманда создает новую таблицу с заданными полями\n" +
                "\t\tФормат: create | tableName | column1 | column2 | ... | columnN \n" +
                "\t\t\tгде: tableName - имя таблицы\n" +
                "\t\t\t     column1 - имя первого столбца записи \n" +
                "\t\t\t     column2 - имя второго столбца записи \n" +
                "\t\t\t     columnN - имя n-го столбца записи \n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\n" +
                "find \n" +
                "\tКоманда для получения содержимого указанной таблицы\n" +
                "\t\tФормат: find | tableName\n" +
                "\t\t\tгде tableName - имя таблицы\n" +
                "\t\tФормат вывода: табличка в консольном формате\n" +
                "\t\t\t\t       +--------+---------+------------------+\n" +
                "\t\t\t\t       +  col1  +  col2   +       col3       +\n" +
                "\t\t\t\t       +--------+---------+------------------+\n" +
                "\t\t\t\t       +  123   +  stiven +     pupkin       +\n" +
                "\t\t\t\t       +  345   +  eva    +     pupkina      +\n" +
                "\t\t\t\t       +--------+---------+------------------+\n" +
                "insert\n" +
                "\tКоманда для вставки одной строки в заданную таблицу\n" +
                "\t\tФормат: insert | tableName | column1 | value1 | column2 | value2 | ... | columnN | valueN\n" +
                "\t\t\tгде: tableName - имя таблицы\n" +
                "\t\t\t     column1   - имя первого столбца записи \n" +
                "\t\t\t     value1    - значение первого столбца записи \n" +
                "\t\t\t     column2   - имя второго столбца записи \n" +
                "\t\t\t     value2    - значение второго столбца записи \n" +
                "\t\t\t     columnN   - имя n-го столбца записи \n" +
                "\t\t\t     valueN    - значение n-го столбца записи \n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\n" +
                "update\n" +
                "\tКоманда обновит запись, установив значение column2 = value2, для которой соблюдается условие column1 = value1 \n" +
                "\t\tФормат: update | tableName | column1 | value1 | column2 | value2\n" +
                "\t\t\tгде: tableName - имя таблицы\n" +
                "\t\t\t     column1 - имя столбца записи которое проверяется\n" +
                "\t\t\t     value1  - значение которому должен соответствовать столбец column1 для обновляемой записи\n" +
                "\t\t\t     column2 - имя обновляемого столбца записи \n" +
                "\t\t\t     value2  - значение обновляемого столбца записи \n" +
                "\t\t\t     columnN - имя n-го обновляемого столбца записи \n" +
                "\t\t\t     valueN  - значение n-го обновляемого столбца записи \n" +
                "\t\tФормат вывода: табличный, как при find со старыми значениями обновленных записей.\n" +
                "delete\n" +
                "\tКоманда удаляет одну или несколько записей для которых соблюдается условие column = value\n" +
                "\t\tФормат: delete | tableName | column | value\n" +
                "\t\t\tгде: tableName - имя таблицы\n" +
                "\t\t\t     column - имя столбца записи которое проверяется\n" +
                "\t\t\t     value  - значение которому должен соответствовать столбец column1 для удаляемой записи\n" +
                "\t\tФормат вывода: табличный, как при find со старыми значениями удаляемых записей.\n" +
                "help \n" +
                "\tКоманда выводит в консоль список всех доступных команд\n" +
                "\t\tФормат: help (без параметров)\n" +
                "\t\tФормат вывода: текст, описания команд с любым форматированием\n" +
                "exit \n" +
                "\tКоманда для отключения от БД и выход из приложения\n" +
                "\t\tФормат: exit (без параметров)\n" +
                "\t\tФормат вывода: текстовое сообщение с результатом выполнения операции\n" +
                "\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Приходите еще =).\r\n", getData());
    }

    @Test
    public void testFindWithoutTableName() throws DriverLoadException, Exception, ConnectionFailedException {
        // given
        in.add("connect|test_database|postgres|postgres");
        in.add("find");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Приветствую в SQL-клиенте, написанном по программе обучения на Juja.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Database connection SUCCESS.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Не введена таблица.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
//                "\r\n" +
                "Приходите еще =).\r\n", getData());
    }

    @Test
    public void testFindWithTableName() throws DriverLoadException, Exception, ConnectionFailedException {
        // given
        in.add("connect|test_database|postgres|postgres");
        in.add("find|employee");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Приветствую в SQL-клиенте, написанном по программе обучения на Juja.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Database connection SUCCESS.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "+--------+-------------+---------+--------+\n" +
                "+ emp_id + emp_name    + dept_id + salary +\n" +
                "+--------+-------------+---------+--------+\n" +
                "+ 1      + Jojo        + 20      + 5000   +\n" +
                "+ 2      + Popat Lal   + 30      + 15000  +\n" +
                "+ 3      + Santa Singh + 40      + 25000  +\n" +
                "+ 4      + Banta Singh + 20      + 7500   +\n" +
                "+ 5      + Sohan Lal   + 20      + 15000  +\n" +
                "+ 6      + Kk          + 10      + 12000  +\n" +
                "+ 7      + Bob         + 20      + 35000  +\n" +
                "+ 8      + John        + 30      + 25000  +\n" +
                "+ 9      + Smith       + 40      + 5000   +\n" +
                "+--------+-------------+---------+--------+\n" +
                "\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Приходите еще =).\r\n", getData());
    }

    @Test
    public void testTables() throws DriverLoadException, Exception, ConnectionFailedException {
        // given
        in.add("connect|test_database|postgres|postgres");
        in.add("tables");
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Приветствую в SQL-клиенте, написанном по программе обучения на Juja.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Database connection SUCCESS.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "+----------+\n" +
                "+ tables   +\n" +
                "+----------+\n" +
                "+ users    +\n" +
                "+ employee +\n" +
                "+----------+\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
                "Приходите еще =).\r\n", getData());
    }


    @Test
    public void testExit() throws DriverLoadException, Exception, ConnectionFailedException {
        // given
        in.add("exit");

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Приветствую в SQL-клиенте, написанном по программе обучения на Juja.\r\n" +
                "Введите необходимую команду. Для справки введите help. Для выхода введите exit.\r\n" +
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
