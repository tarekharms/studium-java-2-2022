package vorlesungsverzeichnis;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception
    {
        Vorlesungsverzeichnis verzeichnis = new Vorlesungsverzeichnis("./src/vorlesungsverzeichnis/vorlesungen.txt");

        //printMap(verzeichnis.groupToTitles());
        //printMap(verzeichnis.multipleTitles());

        printList(verzeichnis.descendingTitles());
    }

    private static void printMap(Map<String, List<String>> map)
    {
        Set<String> keys = map.keySet() ;

        for(String key : keys)
        {
            System.out.print(key + ": ");

            for(String title : map.get(key))
            {
                System.out.print(title + ", ");
            }

            System.out.println();
        }
    }

    private static void printList(List<String> list)
    {
        for(String string : list)
        {
            System.out.println(string);
        }
    }
}
