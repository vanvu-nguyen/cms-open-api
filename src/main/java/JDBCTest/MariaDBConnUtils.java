package JDBCTest;

import Commons.SampleData;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaDBConnUtils {
    public static Connection getMariaDBConnection() {
        String hostName = SampleData.DB_HOST_NAME;
        String dbName = SampleData.DB_NAME;
        String userName = SampleData.DB_USER_NAME;
        String password = SampleData.DB_PASSWORD;
        return getMariaDBConnection(hostName,dbName, userName, password);
    }
    private static Connection getMariaDBConnection(String hostName, String dbName, String userName, String password) {
        Connection conn = null;
        try {
            String connectionURL = "jdbc:mariadb://" + hostName + ":3306/" + dbName;
            conn = DriverManager.getConnection(connectionURL, userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        } return conn;
    }
}
