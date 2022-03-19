package grosseZahl;

public class Main {
    public static void main(String[] args) {
        GrosseZahl zahlA = new GrosseZahl(15);
        System.out.println("A: " + zahlA.toString());

        GrosseZahl zahlB = new GrosseZahl(42);
        System.out.println("43 % 15 = " + zahlB.moduloEinfach(zahlA));

        GrosseZahl zahlRek = new GrosseZahl(
                zahlA.rekursivDividieren(new int[] {3, 2, 1}, new GrosseZahl(0), 0, zahlA)
        );

        System.out.println(zahlRek);

        //GrosseZahl zahlB = new GrosseZahl(1);
        //System.out.println("B: " + zahlB.toString());

        GrosseZahl zahlC = zahlA.sliceLeft(5);

        System.out.println("Erste 2 Ziffern Zahl A: " + zahlC.toString());

    /*
        if(zahlB.equal(zahlA))
            System.out.println("B(" + zahlB + ") ist gleich A(" + zahlA + ")");
        else
            System.out.println("B(" + zahlB + ") ist nicht gleich A(" + zahlA + ")");

        //*/
    }
}
