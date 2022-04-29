package modulbeschreibungen;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args)
    {
        Modulbeschreibungen modulebeschreibungen = new Modulbeschreibungen("./src/modulbeschreibungen/module.txt");

        Set<String> zertifikate = modulebeschreibungen.getZertifikate("BI");

        Set<String> verzahnteModule = modulebeschreibungen.getVerzahnteModule();

        System.out.println("Verzahnte Module: " + verzahnteModule.size());
        for(String string : verzahnteModule)
        {
            System.out.println(string);
        }

        Map<Integer, Integer> ectsPunkte = modulebeschreibungen.getECTS("BI");

        printMap(ectsPunkte);

        System.out.println(modulebeschreibungen.getAnzahlModule("BI", true));
        System.out.println(modulebeschreibungen.getAnzahlVeranstaltungen("BI", true));
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
