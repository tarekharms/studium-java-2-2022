package grosseZahl;

public class Main {
    public static void main(String[] args) {
        GrosseZahl zahlA = new GrosseZahl("111");
        System.out.println("A: " + zahlA.toString());

        GrosseZahl zahlB = new GrosseZahl(102);
        System.out.println("B: " + zahlB.toString());

        if(zahlB.less(zahlA))
            System.out.println("B Kleiner als A");
        else
            System.out.println("B ist nicht kleiner als A");

        GrosseZahl zahlC = zahlA.mult(zahlB);

        System.out.println("A * B = " + zahlC.toString());

    }
}
