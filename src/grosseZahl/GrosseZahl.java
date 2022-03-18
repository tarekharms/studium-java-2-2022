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

    public GrosseZahl(String string)
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

    public GrosseZahl(int[] zahl)
    {
        this.zahl = zahl;
        this.cutLeadingZeros();
    }

    public GrosseZahl(int zahl)
    {
        int stellen = 0;
        int dezimal = 1;

        do
        {
            stellen++;
            dezimal *= 10;
        }
        while(zahl / dezimal > 0);

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
            for(int i = this.getLaenge() - 1; i >= 0; i--)
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

    public GrosseZahl mult(GrosseZahl b)
    {
        GrosseZahl ergebnis = new GrosseZahl(0);

        for(int i = 0; i < b.getLaenge(); i++)
        {
            ergebnis = ergebnis.add(
                    this.mult_single(b.getZiffer(i)).shift(i)
            );
        }

        return ergebnis;
    }

    private GrosseZahl mult_single(int faktor)
    {
        GrosseZahl ergebnis = new GrosseZahl(0);

        for(int i = 0; i < faktor; i++)
        {
            ergebnis = ergebnis.add(this);
        }

        return ergebnis;
    }

    private GrosseZahl shift(int positions)
    {
        int[] zahlx10 = new int[this.zahl.length + positions];

        for(int i = 0; i < positions; i++)
        {
            zahlx10[i] = 0;
        }

        for(int i = 0; i < this.zahl.length; i++)
        {
            zahlx10[i+positions] = this.zahl[i];
        }

        return  new GrosseZahl(zahlx10);
    }

    public GrosseZahl ggT(GrosseZahl b)
    {
        return new GrosseZahl(0);
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

        for(int i = this.zahl.length - 1; i > 0; i--)
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
