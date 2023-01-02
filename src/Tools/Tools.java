package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Tools {
    public static Connection conn;
    public static void connectingToDatabase(String url, String user, String pwd) {
        try {
            System.out.println("Connecting to Database ...");
            Properties props = new Properties();
            props.setProperty("password", pwd);
            props.setProperty("user", user);
            conn = DriverManager.getConnection(url, props);
            System.out.println("Connected with Database!");
        }catch (SQLException ex) {
            System.out.println("Fehler beim Verbinden mit der Datenbank");
            System.out.println(ex);
        }
    }
}
