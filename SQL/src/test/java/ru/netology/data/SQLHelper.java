package ru.netology.data;

import lombok.SneakyThrows;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    static QueryRunner runner = new QueryRunner();

    private static Connection connectToDB() throws SQLException {
        return  DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/db",
                "user",
                "qwerty123");
    }

    @SneakyThrows
    public String getCodeByLogin(String login){

        String id = findIDByLogin(login);
        String query = "SELECT code FROM auth_codes WHERE user_id="+"\'"+id+"\' ORDER BY created DESC LIMIT 1";
        String result = null;
        try (var conn = connectToDB())

        { result = runner.query(conn, query, new ScalarHandler<>());}
        return result;
    }

    @SneakyThrows
    private String findIDByLogin(String login){
        String query = "SELECT id FROM users WHERE login="+"\'"+login+"\'";
        String result = null;
        try (var conn = connectToDB())
        {
            result = runner.query(conn, query, new ScalarHandler<>());
        }
        return result;
    }

    @SneakyThrows
    public static void cleanDB(){
        var conn = connectToDB();
        runner.execute(conn, "DELETE FROM auth_codes");
        runner.execute(conn, "DELETE FROM card_transactions");
        runner.execute(conn, "DELETE FROM cards");
        runner.execute(conn, "DELETE FROM users");
    }


}
