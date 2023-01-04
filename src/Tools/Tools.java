package Tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Tools {
    public static Connection conn;
    public static void connectingToDatabase(String url, String user, String pwd) throws SQLException {
            System.out.println("Connecting to Database ...");
            Properties props = new Properties();
            props.setProperty("password", pwd);
            props.setProperty("user", user);
            conn = DriverManager.getConnection(url, props);
            System.out.println("Connected with Database!");
    }

    public static void initializeDatabase() throws SQLException {
        var workingDir = System.getProperty("user.dir");
        var sqlCommandsFilePath = Paths.get(workingDir, "src", "DB-Skripte", "Create_Table_Postgres");
        String sqlCommands;
        try {
            sqlCommands = Files.readString(sqlCommandsFilePath);
        }
        catch(IOException io)
        {
            System.err.println("Failed to grab file to initialize database because of io exception:" + io.getMessage());
            io.printStackTrace();
            return;
        }
        try (var statement = conn.createStatement()){
            statement.execute(sqlCommands);
        }
        catch(Exception e)
        {
            System.err.println("Failed to create initial databases with exception: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
