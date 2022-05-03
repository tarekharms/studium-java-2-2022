package vorlesung_04_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)
    {
        //fileLines();

        try
        {
            zeitThread();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private static void zeitThread() throws IOException
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Zeitgeber zeitgeber = new Zeitgeber();

        zeitgeber.start();

        while(true)
        {
            String line = input.readLine();

            if(line.equals("e"))
            {
                zeitgeber.stoppen();
                break;
            }
        }
    }

    private static void fileLines()
    {
        try {
            System.out.println("NICHT LAZY: ");
            for(String line : new TextfileLines("./src/vorlesung_04_29/text.txt"))
                System.out.println(line);

            System.out.println("LAZY: ");
            for(String line : new TextfileLinesLazy("./src/vorlesung_04_29/text.txt"))
                System.out.println(line);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
