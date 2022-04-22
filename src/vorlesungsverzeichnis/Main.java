package vorlesungsverzeichnis;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception
    {
        try
        {
            Vorlesungsverzeichnis verzeichnis = new Vorlesungsverzeichnis("./src/vorlesungsverzeichnis/vorlesungen.txt");
            printMap(verzeichnis.multipleTitles());
            //printList(verzeichnis.descendingTitles());
        }
        catch (TextFileFormatException ex)
        {
            System.out.println(ex.getMessage());
        }

        //printMap(verzeichnis.groupToTitles());
        //printMap(verzeichnis.multipleTitles());

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
