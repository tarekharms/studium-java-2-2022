package grosseZahl;

public class GrosseZahl {
    private int[] zahl;

    public int getZiffer(int i)
    {
        return this.zahl[i];
    }

    public int getLaenge()
    {
        return this.zahl.length;
    }

    GrosseZahl(String string)
    {
        this.zahl = new int[string.length()];

        for(int i = 0; i < string.length(); i++)
        {
            int ziffer = Character.getNumericValue((string.charAt(i)));

            if(ziffer > 9)
            {
                System.out.println("FEHLER");
            }
            else
            {
                this.zahl[string.length() - 1 - i] = ziffer;
            }

        }

        this.cutLeadingZeros();
    }

    GrosseZahl(int[] zahl)
    {
        this.zahl = zahl;
        this.cutLeadingZeros();
    }

    GrosseZahl(int zahl)
    {
        int stellen = 0;
        int dezimal = 1;

        while(zahl / dezimal > 0)
        {
            stellen++;
            dezimal *= 10;
        }

        this.zahl = new int[stellen];

        for(int i = 0; i < stellen; i++)
        {
            this.zahl[i] = zahl % 10;
            zahl /= 10;
        }

        this.cutLeadingZeros();
    }

    public boolean less(GrosseZahl b)
    {
        if(b.getLaenge() > this.getLaenge())
            return true;
        else if(b.getLaenge() < this.getLaenge())
            return false;
        else
        {
            for(int i = 0; i < this.getLaenge(); i++)
            {
                if(b.getZiffer(i) < this.getZiffer(i))
                    return false;
                else if(b.getZiffer(i) > this.getZiffer(i))
                    return true;
            }

            return false;
        }
    }

    public GrosseZahl add(GrosseZahl b)
    {
        int maxStellen = this.getLaenge();
        int minStellen = b.getLaenge();
        GrosseZahl groessereZahl = this;

        if(b.getLaenge() > this.getLaenge())
        {
            minStellen = maxStellen;
            maxStellen = b.getLaenge();
            groessereZahl = b;
        }

        maxStellen++;

        int[] neueZahl = new int[maxStellen];
        int uebertrag = 0;

        for(int i = 0; i < minStellen; i++)
        {
            int ergebnis = this.getZiffer(i) + b.getZiffer(i) + uebertrag;
            uebertrag = ergebnis / 10;
            neueZahl[i] = ergebnis % 10;
        }

        for(int i = minStellen; i < maxStellen - 1; i++)
        {
            int ergebnis = groessereZahl.getZiffer(i) + uebertrag;
            uebertrag = ergebnis / 10;
            neueZahl[i] = ergebnis % 10;
        }

        neueZahl[maxStellen - 1] = uebertrag;


        return new GrosseZahl(neueZahl);
    }

    public String toString()
    {
        String string = "";

        for(int i = this.zahl.length - 1; i >= 0; i--)
        {
            string += this.zahl[i];
        }

        return string;
    }

    private void cutLeadingZeros()
    {
        int stellen = this.zahl.length;

        for(int i = this.zahl.length - 1; i >= 0; i--)
        {
            if(this.zahl[i] != 0)
                break;

            stellen--;
        }

        if(stellen != this.zahl.length) {
            int[] zahlen = new int[stellen];

            for (int i = 0; i < stellen; i++) {
                zahlen[i] = this.zahl[i];
            }

            this.zahl = zahlen;
        }
    }
}
