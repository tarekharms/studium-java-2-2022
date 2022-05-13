package klausurenserver;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TeilnahmeDatenCLI {
    private Teilnahmedaten teilnahmedaten;
    private boolean runs;

    public boolean isRunning()
    {
        return this.runs;
    }

    public TeilnahmeDatenCLI()
    {
        this.teilnahmedaten = new Teilnahmedaten();
        this.runs = this.laden("./teilnahmen.txt");
    }

    public String command(String command)
    {
        command = command.toLowerCase().trim();

        String answer = "";

        String[] bestandteile = command.split(" ", 3);

        String action = bestandteile[0];
        String mail = "";
        String zahlen = "";

        if(bestandteile.length >= 2)
        {
            mail = bestandteile[1];
        }

        if(bestandteile.length >= 3)
        {
            zahlen = bestandteile[2];
        }

        if(action.equals("put"))
        {
            if(bestandteile.length == 3)
            {
                answer = this.put(mail, zahlen);
            }
            else
            {
                return "0";
            }
        }
        else if(action.equals("get"))
        {
            if(bestandteile.length == 2)
            {
                answer = this.get(mail);
            }
            else
            {
                return "0";
            }
        }
        else if(action.equals("del"))
        {
            if(bestandteile.length == 2)
            {
                answer = this.del(mail);
            }
            else
            {
                return "0";
            }
        }
        else if(action.equals("getall"))
        {
            if(bestandteile.length == 1)
            {
                answer = this.getAll();
            }
            else
            {
                return "0";
            }
        }
        else if(action.equals("stop"))
        {
            return this.stop();
        }


        return answer;
    }

    private String put(String mail, String zahlen)
    {
        String answer = "";

        String[] zahlenSplitted = zahlen.split(",");
        Set<Integer> zahlenSet = new HashSet<>();

        for(String zahl : zahlenSplitted)
        {
            try
            {
                zahlenSet.add(Integer.parseInt(zahl.trim()));
            }
            catch (NumberFormatException e)
            {
                return "0";
            }
        }

        Set<Integer> alteZahlen = this.teilnahmedaten.put(mail, zahlenSet);

        if(alteZahlen == null)
        {
            return "1";
        }

        answer = "1 ";

        Iterator<Integer> integerIterator = alteZahlen.iterator();

        while (integerIterator.hasNext())
        {
            answer += integerIterator.next();

            if(integerIterator.hasNext())
            {
                answer += ",";
            }
        }

        return answer;
    }

    private String get(String mail)
    {
        Set<Integer> teilnahmen = this.teilnahmedaten.get(mail);

        if(teilnahmen == null)
        {
            return "0";
        }

        String answer = "1 ";

        Iterator<Integer> integerIterator = teilnahmen.iterator();

        while (integerIterator.hasNext())
        {
            answer += integerIterator.next();

            if(integerIterator.hasNext())
            {
                answer += ",";
            }
        }

        return answer;
    }

    private String del(String mail)
    {
        Set<Integer> teilnahmen = this.teilnahmedaten.del(mail);

        if(teilnahmen == null)
        {
            return "0";
        }

        String answer = "1 ";

        Iterator<Integer> integerIterator = teilnahmen.iterator();

        while (integerIterator.hasNext())
        {
            answer += integerIterator.next();

            if(integerIterator.hasNext())
            {
                answer += ",";
            }
        }

        return answer;
    }

    private String getAll()
    {
        String answer = "";
        Set<Set<Integer>> alleTeilnahmen = this.teilnahmedaten.getAll();

        if(alleTeilnahmen.size() == 0)
        {
            return "0";
        }
        else
        {
            answer = "1 ";
            Iterator<Set<Integer>> alleTeilnahmenIterator = alleTeilnahmen.iterator();

            while(alleTeilnahmenIterator.hasNext())
            {
                Set<Integer> teilnahme = alleTeilnahmenIterator.next();
                Iterator teilahmenIterator = teilnahme.iterator();

                answer += "[";

                while (teilahmenIterator.hasNext())
                {
                    answer += teilahmenIterator.next();

                    if(teilahmenIterator.hasNext())
                    {
                        answer += ",";
                    }
                }

                answer += "]";

                if (alleTeilnahmenIterator.hasNext())
                {
                    answer += ",";
                }
            }

        }

        return answer;
    }

    private String stop()
    {
        if(this.speichern("./teilnahmen.txt"))
        {
            this.runs = false;
            return "1";
        }
        else
        {
            return "0";
        }
    }

    private boolean laden(String pfad)
    {
        try {
            String zeile;

            BufferedReader reader = new BufferedReader(new FileReader(pfad));

            while ((zeile = reader.readLine()) != null)
            {
                this.command(zeile);
            }

            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    private boolean speichern(String pfad)
    {
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter(pfad));

            Set<String> teilnehmers = this.teilnahmedaten.getAllEmails();

            for(String teilnehmer : teilnehmers)
            {
                String line = "put " + teilnehmer + " ";

                Iterator<Integer> integerIterator = this.teilnahmedaten.get(teilnehmer).iterator();

                while (integerIterator.hasNext())
                {
                    line += integerIterator.next();

                    if (integerIterator.hasNext())
                    {
                        line += ",";
                    }
                }

                writer.println(line);
            }

            writer.close();
            return true;
        }
        catch (IOException e)
        {
            return false;
        }

    }
}
