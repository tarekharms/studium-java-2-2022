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

    public boolean equal(GrosseZahl b)
    {
        return (!this.less(b) && !b.less(this));
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

    public GrosseZahl dividieren(GrosseZahl divisor)
    {
        return new GrosseZahl(
                rekursivDividieren(this.zahl, new GrosseZahl(0), 0, divisor)
        );
    }

    public GrosseZahl modulo(GrosseZahl divisor)
    {
        GrosseZahl quotient = this.dividieren(divisor);

        return this.subtract(divisor.mult(quotient));
    }

    public static int[] rekursivDividieren(int[] ziffern, GrosseZahl zwischenergebnis, int stelle, GrosseZahl divisor)
    {
        if(ziffern.length == 0) return new int[stelle];

        zwischenergebnis = zwischenergebnis.shiftLeft(1);
        zwischenergebnis = zwischenergebnis.add(new GrosseZahl(ziffern[ziffern.length-1]));

        int quotient = zwischenergebnis.dividierenEinfach(divisor);

        if(quotient > 0)
        {
            zwischenergebnis = zwischenergebnis.subtract(divisor.mult_single(quotient));
        }

        int[] neueZiffern = new int[ziffern.length - 1];

        for(int i = 0; i < neueZiffern.length; i++)
        {
            neueZiffern[i] = ziffern[i];
        }

        int[] array = rekursivDividieren(neueZiffern, zwischenergebnis, stelle+1, divisor);
        array[array.length - stelle - 1] = quotient;

        return array;
    }

    public int dividierenEinfach(GrosseZahl divisor)
    {
        int quotient = 0;
        GrosseZahl zwischen = new GrosseZahl(0);

        while(!this.less(zwischen.add(divisor)))
        {
            zwischen = zwischen.add(divisor);
            quotient++;

        }

        return quotient;
    }

    public GrosseZahl moduloEinfach(GrosseZahl divisor)
    {
        GrosseZahl zwischen = new GrosseZahl(0);

        do
        {
            zwischen = zwischen.add(divisor);
        }
        while(zwischen.add(divisor).less(this));

        return this.subtract(zwischen);
    }

    public GrosseZahl sliceLeft(int laenge)
    {
        if(laenge > this.getLaenge()) laenge = this.getLaenge();

        int[] ziffern = new int[laenge];

        for(int i = 0; i < laenge; i++)
        {
            ziffern[i] = this.zahl[this.zahl.length - laenge + i];
        }

        return new GrosseZahl(ziffern);
    }

    public GrosseZahl subtract(GrosseZahl b)
    {
        if(this.less(b) || this.equal(b)) return new GrosseZahl(0);

        int[] neueZahl = new int[this.getLaenge()];
        int uebertrag = 0;

        for(int i = 0; i < b.getLaenge(); i++)
        {
            int ergebnis = 10 + this.getZiffer(i) - b.getZiffer(i) - uebertrag;

            uebertrag = 1 - ergebnis / 10;
            neueZahl[i] = ergebnis % 10;
        }

        for(int i = b.getLaenge(); i < this.getLaenge(); i++)
        {
            int ergebnis = 10 + this.getZiffer(i) - uebertrag;
            uebertrag = 1 - ergebnis / 10;
            neueZahl[i] = ergebnis % 10;
        }

        return new GrosseZahl(neueZahl);
    }

    public GrosseZahl mult(GrosseZahl b)
    {
        GrosseZahl ergebnis = new GrosseZahl(0);

        for(int i = 0; i < b.getLaenge(); i++)
        {
            ergebnis = ergebnis.add(
                    this.mult_single(b.getZiffer(i)).shiftLeft(i)
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

    private GrosseZahl shiftLeft(int positions) {
        int[] zahlx10 = new int[this.zahl.length + positions];

        for (int i = 0; i < positions; i++) {
            zahlx10[i] = 0;
        }

        for (int i = 0; i < this.zahl.length; i++) {
            zahlx10[i + positions] = this.zahl[i];
        }

        return new GrosseZahl(zahlx10);
    }

    public GrosseZahl ggT(GrosseZahl b)
    {
        if(b.equal(new GrosseZahl(0)))
        {
            return this;
        }

        return b.ggT(this.modulo(b));
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
