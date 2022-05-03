package modulbeschreibungen;

import java.util.ArrayList;
import java.util.List;

public class Modul {

    private String bezeichnung;
    private String kuerzel;
    private String studiengang;
    private String semester;
    private String art;
    private double ects;
    private String pruefungsform;
    private String verantwortlicher;
    private List<Veranstaltung> veranstaltungen;

    public String getBezeichnung()
    {
        return this.bezeichnung;
    }

    public String getSemester()
    {
        return this.semester;
    }

    public double getECTS() { return this.ects; }

    public String getStudiengang()
    {
        return this.studiengang;
    }

    public String getArt()
    {
        return this.art;
    }

    public int getSws()
    {
        int sws = 0;

        for(Veranstaltung veranstaltung : this.veranstaltungen)
        {
            sws += veranstaltung.getSws();
        }

        return sws;
    }

    public Modul(List<String> dateiInhalt)
    {
        this.veranstaltungen = new ArrayList<>(dateiInhalt.size() - 1);

        this.parseModulinfo(dateiInhalt.get(0));

        for(int i = 1; i < dateiInhalt.size(); i++)
        {
            this.veranstaltungen.add(new Veranstaltung(dateiInhalt.get(i)));
        }

    }

    public boolean isVerzahntMit(Modul modul)
    {
        if(this == modul) return false;

        if(this.getStudiengang().equals(modul.getStudiengang())) return false;

        if(!this.bezeichnung.equals(modul.bezeichnung)) return false;

        if(!this.verantwortlicher.equals(modul.verantwortlicher)) return false;

        if(this.kuerzel.isEmpty() || modul.kuerzel.isEmpty()) return false;

        String thisKuerzel = this.kuerzel.split("-")[0];
        String thatKuerzel = modul.kuerzel.split("-")[0];

        return thisKuerzel.equals(thatKuerzel);
    }

    public int getAnzahlVeranstaltungen()
    {
        return this.veranstaltungen.size();
    }

    private void parseModulinfo(String datenString)
    {
        String[] daten = datenString.split("\\|");

        this.bezeichnung = daten[0];
        this.kuerzel = daten[1];
        this.studiengang = daten[2];
        this.semester = daten[3];
        this.art = daten[4];
        this.ects = Double.parseDouble(daten[5].replace(',', '.'));
        this.pruefungsform = daten[6];
        this.verantwortlicher = daten[7];
    }
}
