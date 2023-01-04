package Backend;

import java.util.Iterator;
import java.util.Map;
import java.util.logging.Filter;

public class DatabaseStuff {
    public static String insertItem(Map<String, Object> map) {
        Iterator<Map.Entry<String, Object>> itKeys = map.entrySet().iterator();
        String nextKey = "";
        Object nextValue ="";
        Integer countToKeyTable = 0;
        Integer countToKeyTableValue = 0;
        Integer countFinalToKeyTable = 0;
        String query = "INSERT INTO ";
        query = query + map.get("table") + " (";
        query = query  + itKeys.next().getKey();
        while (itKeys.hasNext()) {
            countToKeyTable++;
            nextKey = itKeys.next().getKey();
            if(nextKey != "table") {
                query = query + ", " + nextKey;
            } else {
                countFinalToKeyTable = countToKeyTable;
            }
        }
        query = query + ") VALUES (";
        Iterator<Map.Entry<String, Object>> itValues = map.entrySet().iterator();
        query = query + "'" + itValues.next().getValue() + "'";
        while (itValues.hasNext()) {
            countToKeyTableValue++;
            nextValue = itValues.next().getValue();
            if(countToKeyTableValue != countFinalToKeyTable) {
                query = query + ", '" + nextValue + "'";
            }
        }
        query = query + ");";
        return query;
    }

    public static String getItem(Filter filter) {
        String s = "";
        return s;
    }
}
