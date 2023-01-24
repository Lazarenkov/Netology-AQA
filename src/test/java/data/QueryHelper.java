package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QueryHelper {

    private QueryHelper() {
    }

    static QueryRunner runner = new QueryRunner();
    static Connection connection = null;

    public static void connectToPostgres() throws SQLException {
        connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/app",
                        "app",
                        "pass");
    }

    public static void connectToMySQL() throws SQLException {
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/app",
                        "app",
                        "pass");
    }

    @SneakyThrows
    public static void cleanDB() {
        var conn = connection;
        runner.execute(conn, "DELETE * FROM credit_request_entity");
        runner.execute(conn, "DELETE * FROM order_entity");
        runner.execute(conn, "DELETE * FROM payment_entity");
    }

    @SneakyThrows
    public static String getStatusOfLastLoanRequest() {
        String query = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        String result;
        try (var conn = connection) {
            result = runner.query(conn, query, new ScalarHandler<>());
        }
        return result;
    }

    @SneakyThrows
    public static String getStatusOfLastPurchaseRequest() {
        String query = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        String result;
        try (var conn = connection) {
            result = runner.query(conn, query, new ScalarHandler<>());
        }
        return result;
    }

    public static void checkApprovedStatusOfLastPurchaseRequest() {
        String result = getStatusOfLastPurchaseRequest();
        Assertions.assertEquals("APPROVED", result);
    }

    public static void checkApprovedStatusOfLastLoanRequest() {
        String result = getStatusOfLastLoanRequest();
        Assertions.assertEquals("APPROVED", result);
    }

    public static void checkDeclinedStatusOfLastPurchaseRequest() {
        String result = getStatusOfLastPurchaseRequest();
        Assertions.assertEquals("DECLINED", result);
    }

    public static void checkDeclinedStatusOfLastLoanRequest() {
        String result = getStatusOfLastLoanRequest();
        Assertions.assertEquals("DECLINED", result);
    }


}
