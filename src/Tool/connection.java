package Tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "qwer";
    private Connection conn = null;
    //获得MySQL数据库连接
    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
