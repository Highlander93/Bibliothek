package Backend;

import Tools.Tools;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class Start {

    public static void startProgram() throws SQLException, IOException, ParseException, IllegalAccessException {
        startConnectionDatabase();
        startGUI();
    }

    private static void startGUI() throws IOException, ParseException, IllegalAccessException {
        try {
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
            String auszufuehrendeAktion = sc.nextLine();
            Methods.getAction(auszufuehrendeAktion);
        } catch (CustomizedExceptions.IncorrectSelection ex) {
            System.out.println("Bitte erneut eingeben: \n" +
                    "_______________________________________");
            startGUI();
        }
    }

    private static void startConnectionDatabase() {
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "User";
        String pwd = "pwd";
        Tools.connectingToDatabase(url, user, pwd);
    }
}
