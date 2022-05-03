package modulbeschreibungen;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args)
    {
        //Modulbeschreibungen modulebeschreibungen = new Modulbeschreibungen("./src/modulbeschreibungen/module.txt");
        Modulbeschreibungen modulebeschreibungen = new Modulbeschreibungen("./mb-junit.txt");

        List<Modul> module = modulebeschreibungen.getModule();

        System.out.println(modulebeschreibungen.getJSON("BI"));

        /*
        Map<Integer, Integer> sws = modulebeschreibungen.getECTS("BMT");
        System.out.println(sws.size());
        printMap(sws);
         */
    }

    private static void printMap(Map<Integer, Integer> map)
    {
        Set<Integer> keys = map.keySet() ;

        for(Integer key : keys)
        {
            System.out.print(key + ": " + map.get(key));
            System.out.println();
        }
    }

}
