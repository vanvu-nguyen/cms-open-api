package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaDBConnUtils {
    public static Connection getMariaDBConnection() {
        String hostName = "10.100.116.40";
        String dbName = "infocms_bidv";
        String userName = "infocms_ba_user";
        String password = "Inf0#Cm$2050P@";
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
