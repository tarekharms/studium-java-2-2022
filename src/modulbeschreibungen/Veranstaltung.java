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

    public String getJSON()
    {
        return this.getJSON("");
    }

    public String getJSON(String depth)
    {
        String json = "{\n";

        json += depth + "\t\"titel\": \"" + this.titel + "\",\n";
        json += depth + "\t\"dozenten\": \"" + this.dozenten + "\",\n";
        json += depth + "\t\"sws\": " + this.sws + "\n";

        json += depth + "}";

        return json;
    }
}
