package data;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    // Constructor private để đảm bảo chỉ có một instance (Singleton)
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            // Đổi tên JNDI cho đúng với context.xml
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/workshop1");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy DataSource từ JNDI: " + e.getMessage());
        }
    }

    // Lấy instance duy nhất của ConnectionPool
    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    // Lấy một kết nối từ pool
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Trả lại kết nối vào pool
    public void freeConnection(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
