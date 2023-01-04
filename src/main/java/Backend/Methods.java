package Backend;

import Tools.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Methods {
    public static void getAction(String action) throws CustomizedExceptions.IncorrectSelection, IOException, ParseException, IllegalAccessException {
        switch (action) {
            case "0":
                getAllMediums();
                break;
            case "1":
                pushNewMedium();
                break;
            case "2":
                deleteMedium();
                break;
            case "3":
                rentMedium();
                break;
            case "4":
                giveMediumBack();
                break;
            case "q":
                break;
            default:
                throw new CustomizedExceptions.IncorrectSelection("Diese Aktion gibt es nicht!");
        }
    }

    private static void getAllMediums() {

    }

    private static void pushNewMedium() throws IOException, IllegalAccessException {
        MediumDTO mediumDTO = new MediumDTO();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Anzahl an Exemplaren: ");
        mediumDTO.setAnzahl(reader.readLine());
        System.out.println("Titel des Mediums: ");
        mediumDTO.setTitel(reader.readLine());
        System.out.println("Name des Autors: ");
        mediumDTO.setAutor(reader.readLine());
        System.out.println("Ist dieses medium digital(true=digital, false=analog): ");
        mediumDTO.setDigital(reader.readLine());
        System.out.println("Wann ist das Buch erschienen(xx.xx.xxxx): ");
        mediumDTO.setErscheinungsdatum(reader.readLine());
        mediumDTO.setTable(mediumDTO.getTable());
        Map<String, Object> map = new HashMap<>();


        for (Field field : mediumDTO.getClass().getDeclaredFields()) {
            Object value = field.get(mediumDTO);
            if (value != null) {
                map.put(field.getName(), value);
            }
        }

        String test = DatabaseStuff.insertItem(map);
        try {
            Statement st = Tools.conn.createStatement();
            st.executeUpdate(test);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteMedium() {

    }

    private static void rentMedium() {

    }

    private static void giveMediumBack() {

    }
}
