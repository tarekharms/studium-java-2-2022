package grosseZahl;

public class Main {
    public static void main(String[] args) {
        GrosseZahl zahlA = new GrosseZahl("17");
        System.out.println("A: " + zahlA.toString());

        GrosseZahl zahlB = new GrosseZahl(100);
        System.out.println("B: " + zahlB.toString());

        GrosseZahl zahlC = zahlA.mult(zahlB);

        System.out.println("A * B = " + zahlC.toString());

        /*
        if(zahlB.less(zahlA))
            System.out.println("B(" + zahlB + ") ist Kleiner als A(" + zahlA + ")");
        else
            System.out.println("B(" + zahlB + ") ist nicht Kleiner als A(" + zahlA + ")");
        */

    }
}
