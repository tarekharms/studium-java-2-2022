package vorlesungsverzeichnis;

import java.io.*;
import java.util.*;

public class Vorlesungsverzeichnis {
    Set<Vorlesung> vorlesungen;

    public Vorlesungsverzeichnis(String pfad) throws TextFileFormatException, IOException
    {
        this.vorlesungen = new HashSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(pfad));
        int zeile = 1;

        for (String line=reader.readLine(); line!=null; line=reader.readLine())
        {
            String[] splitted = line.split(":");

            if(splitted.length > 4) throw new TextFileFormatException("Mehr als 4 Variablen in Zeile: " + zeile);
            if(splitted.length < 4) throw new TextFileFormatException("Weniger als 4 Variablen in Zeile: " + zeile);

            int teilnehmer = 0;

            try
            {
                teilnehmer = Integer.parseInt(splitted[3]);
            }
            catch (Exception exception)
            {
                throw new TextFileFormatException("Anzahl Teilnehmer keine Ganzzahl in Zeile: " + zeile);
            }

            if(
                    splitted[0].length() == 0 ||
                    splitted[1].length() == 0 ||
                    splitted[2].length() == 0
            ){
                throw new TextFileFormatException("Leeres Feld in Zeile: " + zeile);
            }

            this.vorlesungen.add(new Vorlesung(
                    splitted[0],
                    splitted[1],
                    splitted[2],
                    teilnehmer
            ));

            zeile++;
        }

        reader.close();
    }

    public List<String> titles()
    {
        Set<String> titleSet = new HashSet<>();

        for(Vorlesung vorlesung : this.vorlesungen)
        {
            titleSet.add(vorlesung.getTitel());
        }

        List<String> titles = new ArrayList<>(titleSet);

        Collections.sort(titles);

        return titles;
    }

    public Set<String> workaholics()
    {
        Set<String> dozenten = new HashSet<>();
        Set<String> workaholics = new HashSet<>();

        for(Vorlesung vorlesung : this.vorlesungen)
        {
            String dozent = vorlesung.getDozent();

            if(dozenten.contains(dozent))
            {
                workaholics.add(dozent);
            }
            else
            {
                dozenten.add(dozent);
            }

        }

        return workaholics;
    }

    public Map<String, List<String>> groupToTitles()
    {
        Set<String> gruppen = new HashSet<>();
        Map<String, List<String>> gruppenTitel = new HashMap<>();

        for(Vorlesung vorlesung : this.vorlesungen)
        {
            gruppen.add(vorlesung.getStudienGruppe());
        }

        for(String gruppe : gruppen)
        {
            gruppenTitel.put(gruppe, new ArrayList<>());
        }

        for(Vorlesung vorlesung : this.vorlesungen)
        {
            gruppenTitel.get(vorlesung.getStudienGruppe()).add(vorlesung.getTitel());
        }

        return gruppenTitel;
    }

    private Set<String> titelMehrereDozenten()
    {
        Set<String> titels = new HashSet<>();
        Set<String> multiple = new HashSet<>();

        for(Vorlesung vorlesung : this.vorlesungen)
        {
            String titel = vorlesung.getTitel();

            if(titels.contains(titel))
            {
                multiple.add(titel);
            }
            else
            {
                titels.add(titel);
            }

        }

        return multiple;
    }

    public Map<String, List<String>> multipleTitles()
    {
        Map<String, List<String>> titelDozenten = new HashMap<>();
        Set<String> titels = this.titelMehrereDozenten();

        for(String titel : titels)
        {
            titelDozenten.put(titel, new ArrayList<>());
        }
        
        for(Vorlesung vorlesung : this.vorlesungen)
        {
            if(titels.contains(vorlesung.getTitel()))
            {
                titelDozenten.get(vorlesung.getTitel()).add(vorlesung.getDozent());
            }
        }

        return titelDozenten;
    }

    public List<String> descendingTitles()
    {
        List<Vorlesung> vorlesungen = new ArrayList<>(this.vorlesungen);
        List<String> titelliste = new ArrayList<>(vorlesungen.size());

        Collections.sort(vorlesungen, Collections.reverseOrder(new VorlesungTeilnehmerComparator()));

        for(Vorlesung vorlesung : vorlesungen)
        {
            titelliste.add(vorlesung.getTitel());
        }

        return titelliste;
    }
}
