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
                this.zahl[i] = ziffer;
            }

        }
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
            this.zahl[stellen - 1 - i] = zahl % 10;
            zahl /= 10;
        }

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


        return new GrosseZahl(1);
    }

    public String toString()
    {
        String string = "";

        for(int i = 0; i < this.zahl.length; i++)
        {
            string += this.zahl[i];
        }

        return string;
    }
}
