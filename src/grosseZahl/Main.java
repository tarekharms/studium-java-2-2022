package grosseZahl;

public class Main {
    public static void main(String[] args) {
        GrosseZahl zahlA = new GrosseZahl(8345);
        System.out.println("A: " + zahlA);

        GrosseZahl zahlB = new GrosseZahl(15);
        System.out.println("B: " + zahlB);

        GrosseZahl zahlADurchB = zahlA.dividieren(zahlB);
        GrosseZahl zahlAModuloB = zahlA.modulo(zahlB);

        System.out.println(zahlA + " / " + zahlB + " = " + zahlADurchB);
        System.out.println(zahlA + " % " + zahlB + " = " + zahlAModuloB);
    }
}
