package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    static QueryRunner runner = new QueryRunner();

    private static Connection connectToDB() throws SQLException {
        return DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/app",
                        "app",
                        "pass");
    }

    @SneakyThrows
    public static void cleanDB() {
        var conn = connectToDB();
        runner.execute(conn, "DELETE * FROM credit_request_entity");
        runner.execute(conn, "DELETE * FROM order_entity");
        runner.execute(conn, "DELETE * FROM payment_entity");
    }

    @SneakyThrows
    public static String getStatusOfLastCreditRequest() {
        String query = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        String result;
        try (var conn = connectToDB()) {
            result = runner.query(conn, query, new ScalarHandler<>());
        }
        return result;
    }

    @SneakyThrows
    public static String getStatusOfLastPayment() {
        String query = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        String result;
        try (var conn = connectToDB()) {
            result = runner.query(conn, query, new ScalarHandler<>());
        }
        return result;
    }


}
