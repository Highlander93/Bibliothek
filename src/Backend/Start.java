package Backend;

import Tools.Tools;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;


public class Start {

    public static void startProgram() throws IOException, ParseException, IllegalAccessException {
        try {
            startConnectionDatabase();
        } catch (SQLException e) {
            System.err.println("Could not connect to db or create tables! Will abort further execution.");
            return;
        }
        startGUI();
    }

    private static void startGUI() throws IOException, ParseException, IllegalAccessException {
        try {
            String auszufuehrendeAktion;
            do {
                System.out.println(
                        "0: Alle Bücher auflisten \n" +
                                "1: Neues Buch anlegen \n" +
                                "2: Buch löschen \n" +
                                "3: Bestimmtes Buch ausleihen \n" +
                                "4: Ausgeliehenes Buch wieder zurückbringen \n" +
                                "q: Um das Programm zu beenden"
                );
                System.out.println("Welche Aktion möchten Sie ausführen?");
                Scanner sc = new Scanner(System.in);
                auszufuehrendeAktion = sc.nextLine();
                if (auszufuehrendeAktion.equals("q"))
                {
                    return;
                }
                Methods.getAction(auszufuehrendeAktion);
            } while(auszufuehrendeAktion != "q");
        } catch (CustomizedExceptions.IncorrectSelection ex) {
            System.out.println("Bitte erneut eingeben: \n" +
                    "_______________________________________");
            startGUI();
        }
        System.out.println("Programmende.");
    }

    /**
     * Connect to database, and in case an admin user or local server is select, execute initialization steps.
     * @throws SQLException if sql connection could not be established or initialization went wrong
     * @throws IllegalArgumentException if any choice was wrong/not listed
     */
    private static void startConnectionDatabase() throws SQLException, IllegalArgumentException {
        System.out.println("Soll ein lokaler Datenbankserver verwendet werden?\n" +
                "1: lokale Datenbank\n" +
                "2: remote Datenbank"
        );
        Scanner sc = new Scanner(System.in);
        String url;
        String user;
        String pwd;
        var serverChoice = sc.hasNext() ? sc.nextLine() : "1";
        switch(serverChoice)
        {
            case "1":
                url = "jdbc:postgresql://localhost/postgres";
                user = "User";
                pwd = "pwd";
                break;
            case "2":
                var remoteLoginData = handleRemoteDatabaseChoice(sc);
                url = remoteLoginData.get("url");
                user = remoteLoginData.get("user");
                pwd = remoteLoginData.get("pwd");
                break;
            default:
                throw new IllegalArgumentException("Cannot handle input '" + serverChoice + "'!");
        }
        Tools.connectingToDatabase(url, user, pwd);

        if (serverChoice.equals("1") || user == "postgres") {
            Tools.initializeDatabase();
        }
    }

    /**
     * Get further user input for the remote database
     * @param inputStreamScanner scan user input in console
     * @return map with keys "url", "user" and "pwd".
     */
    private static HashMap<String, String> handleRemoteDatabaseChoice(Scanner inputStreamScanner)
    {
        System.out.println("Welche remote Datenbank?\n" +
                "1: Alpha\n" +
                "2: Beta\n" +
                "3: Live"
        );
        var dbToUse = "bibliothek";
        var dbChoice = inputStreamScanner.nextLine();
        switch(dbChoice)
        {
            case "1":
                dbToUse += "_alpha";
                break;
            case "2":
                dbToUse += "_beta";
                break;
            case "3":
                break;
            default:
                throw new IllegalArgumentException("Cannot process choice '" + dbChoice + "'!");
        }
        var url = "jdbc:postgresql://steffes.xyz:5432/" + dbToUse;
        System.out.println("Welcher Nutzer?\n" +
                "1: Standard\n" +
                "2: Admin");
        var userChoice = inputStreamScanner.nextLine();
        String user;
        String pwd;
        switch(userChoice) {
            case "1":
                user = "DefaultUser";
                pwd = "KUlKtCZ6H4QxIoDTIpAJ";
                break;
            case "2":
                user = "postgres";
                System.out.println("Bitte Passwort für Adminuser (postgres) angeben:");
                pwd = inputStreamScanner.nextLine();
                break;
            default:
                throw new IllegalArgumentException();
        }
        return new HashMap<>() {{
            put("url", url);
            put("user", user);
            put("pwd", pwd);
        }};
    }
}
