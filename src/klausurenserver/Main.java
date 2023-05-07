package klausurenserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException
    {

        Klausurenserver server = new Klausurenserver(1337);
        server.start();

        /*

        TeilnahmeDatenCLI cli = new TeilnahmeDatenCLI();

        while(cli.isRunning())
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();

            if(line == null) continue;

            System.out.println(cli.command(line));
        }

        /*
        System.out.println(cli.command("getall"));
        System.out.println(cli.command("del max.mueller@stud.hs-emden-leer.de"));
        System.out.println(cli.command("get max.mueller@stud.hs-emden-leer.de"));
        System.out.println(cli.command("del max.mueller@stud.hs-emden-leer.de"));

        /*
        System.out.println("put max.mueller@stud.hs-emden-leer.de 34,45,56");
        System.out.println(daten.put("max.mueller@stud.hs-emden-leer.de", toSet(34, 45, 56)));
        System.out.println("put axel.maier@stud.hs-emden-leer.de 34,56");
        System.out.println(daten.put("axel.maier@stud.hs-emden-leer.de", toSet(34, 56)));
        System.out.println("getall");
        System.out.println(daten.getAll());
        System.out.println("put max.mueller@stud.hs-emden-leer.de 34,45");
        System.out.println(daten.put("max.mueller@stud.hs-emden-leer.de", toSet(34, 45)));
        System.out.println("del max.mueller@stud.hs-emden-leer.de");
        System.out.println(daten.del("max.mueller@stud.hs-emden-leer.de"));
        System.out.println("get max.mueller@stud.hs-emden-leer.de");
        System.out.println(daten.get("max.mueller@stud.hs-emden-leer.de"));
        System.out.println("stop");

         */


    }

    private static Set<Integer> toSet(int... array)
    {
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < array.length; i++)
        {
            set.add(array[i]);
        }

        return set;
    }
}
