package modulbeschreibungen;

import vorlesungsverzeichnis.TextFileFormatException;
import vorlesungsverzeichnis.Vorlesung;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Modulbeschreibungen {
    private List<Modul> module;

    public Modul getModul(int index)
    {
        return this.module.get(index);
    }

    public List<Modul> getModule()
    {
        return this.module;
    }

    public Modulbeschreibungen(String dateipfad)
    {
        this.module = new ArrayList<>();
        try
        {
            this.module = this.loadModule(dateipfad);
            System.out.println("Anzahl Module: " + this.module.size());
        }
        catch (Exception ex)
        {
            System.out.println("EXCEPTION");
            System.out.println(ex.getMessage());
        }
    }

    public Set<String> getZertifikate(String studiengang)
    {
        Set<String> zertifikate = new HashSet<>();
        List<Modul> module = this.getModuleByStudiengang(studiengang);

        for(Modul modul : module)
        {
            if(modul.getArt().contains("Zertifikat"))
            {
                String artString = modul.getArt();
                artString = artString.substring(17, artString.length());
                String[] zertifikateStrings = artString.split("und Zertifikat ");

                if(zertifikateStrings.length > 0)
                {
                    zertifikate.add(zertifikateStrings[0]);

                    for(int i = 1; i < zertifikateStrings.length; i++)
                    {
                        zertifikate.add("Zertifikat " + zertifikateStrings[i]);
                    }

                }
                else
                {
                    zertifikate.add(artString);
                }

            }
        }

        return zertifikate;
    }

    public Set<String> getVerzahnteModule()
    {
        Set<String> verzahnteMoudle = new HashSet<>();

        for(Modul modul : module)
        {
            if(this.pruefeObVerzahntesModul(modul))
            {
                verzahnteMoudle.add(modul.getBezeichnung());
            }
        }

        return verzahnteMoudle;
    }

    private boolean pruefeObVerzahntesModul(Modul modul)
    {
        for(Modul vergleich : this.module)
        {
            if(modul.isVerzahntMit(vergleich)) return true;
        }

        return false;
    }

    public int getAnzahlModule(String studiengang, Boolean pflicht)
    {
        List<Modul> module = this.getModuleByStudiengang(studiengang);

        if(pflicht == null)
        {
            return module.size();
        }
        else
        {
            return filterPflichtModule(module, pflicht).size();
        }
    }

    private static List<Modul> filterPflichtModule(List<Modul> module, Boolean pflicht)
    {
        List<Modul> pflichtModule = new ArrayList<>();

        for(Modul modul : module)
        {
            if(modul.getArt().equals("Pflichtmodul") != !pflicht)
            {
                pflichtModule.add(modul);
            }
        }

        return pflichtModule;
    }

    public int getAnzahlVeranstaltungen(String studiengang, Boolean pflicht)
    {
        List<Modul> studiengangModule = this.getModuleByStudiengang(studiengang);
        List<Modul> gefilterteModule = filterPflichtModule(studiengangModule, pflicht);

        int anzahlVeranstaltungen = 0;

        for(Modul modul : gefilterteModule)
        {
            anzahlVeranstaltungen += modul.getAnzahlVeranstaltungen();
        }

        return anzahlVeranstaltungen;
    }

    public Map<Integer, Integer> getECTS(String studiengang)
    {
        List<Modul> moduleStudiengang = filterPflichtModule(this.getModuleByStudiengang(studiengang), true);

        Map<Integer, Double> ectsPunkte = new HashMap<>();
        Map<Integer, Integer> ectsPunkteGerundet = new HashMap<>();

        for(Modul modul : moduleStudiengang)
        {
            int semester = Integer.parseInt(modul.getSemester());
            double ects = modul.getECTS();

            if(ectsPunkte.containsKey(semester))
            {
                ectsPunkte.put(semester, ectsPunkte.get(semester) + ects);
            }
            else
            {
                ectsPunkte.put(semester, ects);
            }
        }

        for(Integer key : ectsPunkte.keySet())
        {
            double punkte = ectsPunkte.get(key);

            if(punkte > 0)
            {
                ectsPunkteGerundet.put(key, (int)punkte);
            }
        }

        return ectsPunkteGerundet;
    }

    public List<Modul> getModuleByStudiengang(String studiengang)
    {
        List<Modul> module = new ArrayList<>();

        for(Modul modul : this.module)
        {
            if(modul.getStudiengang().equals(studiengang))
            {
                module.add(modul);
            }
        }

        return module;
    }

    private List<Modul> loadModule(String pfad) throws IOException
    {
        List<Modul> module = new ArrayList<>();
        List<String> modulInhalt = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(pfad));

        int zeile = 1;

        for (String line=reader.readLine(); line!=null; line=reader.readLine())
        {
            if(line.isEmpty())
            {
                if(modulInhalt.size() == 0) continue;

                module.add(new Modul(modulInhalt));
                modulInhalt = new ArrayList<>();
            }
            else
            {
                modulInhalt.add(line);
            }

            zeile++;
        }

        reader.close();

        return module;
    }
}
