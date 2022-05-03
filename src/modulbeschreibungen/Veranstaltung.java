package modulbeschreibungen;

public class Veranstaltung {
    private String titel;
    private String dozenten;
    private int sws;

    public int getSws()
    {
        return this.sws;
    }

    public Veranstaltung(String dateiString)
    {
        String[] daten = dateiString.split("\\|");

        this.titel = daten[0];
        this.dozenten = daten[1];
        this.sws = Integer.parseInt(daten[2]);
    }
}
