package modulbeschreibungen;

public class Studiengang {
    private String bezeichnung;
    private int sws;

    public int getSws()
    {
        return this.sws;
    }

    public String getBezeichnung()
    {
        return this.bezeichnung;
    }

    public Studiengang(String bezeichnung, int sws)
    {
        this.bezeichnung = bezeichnung;
        this.sws = sws;
    }
}
