package be.howest.ti.ooansd.exam.data.mysql;
import be.howest.ti.ooansd.exam.util.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    private static final String URL = config.getInstance().get("db.url");
    private static final String USER = config.getInstance().get("db.user");
    private static final String PASSWORD = config.getInstance().get("db.password");

    private MysqlConnection() {
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
