package grosseZahl;

public class Main {
    public static void main(String[] args) {
        GrosseZahl zahlA = new GrosseZahl(250);
        System.out.println("A: " + zahlA);

        GrosseZahl zahlB = new GrosseZahl(25);
        System.out.println("B: " + zahlB);

        System.out.println("GGT 250  23 = " + zahlA.ggT(zahlB));
    }
}
